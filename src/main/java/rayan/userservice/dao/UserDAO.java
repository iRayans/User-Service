package rayan.userservice.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import rayan.userservice.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDAO {

    @PersistenceContext // automatically injects an instance of the EntityManager into the class
    private EntityManager em;

    public Optional<User> create(User user) {
        try {
            em.persist(user);
            em.flush();  // Force Hibernate to assign an ID immediately.
            return Optional.of(user);
        } catch (EntityExistsException e) {
            throw new EntityExistsException(e.getMessage());
        }
    }

    public List<User> findAll() {
        return em.createQuery("SELECT c FROM User c", User.class).getResultList();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Transactional
    public void delete(Long id) {
        var User = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        em.remove(User);
    }

    @Transactional
    public User update(User User) {
        return em.merge(User);
    }


    public boolean emailExists(String email) {
        String jpql = "SELECT COUNT(e) FROM User e WHERE e.email = :email";

        try {
            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        }
    }
}


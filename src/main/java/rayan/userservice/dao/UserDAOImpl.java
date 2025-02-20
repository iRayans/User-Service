package rayan.userservice.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.entity.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {

    @PersistenceContext // automatically injects an instance of the EntityManager into the class
    private EntityManager em;

    @Override
    public Optional<User> create(User user) throws AppServerException {
        try {
            em.persist(user);
            em.flush();  // Force Hibernate to assign an ID immediately.
            return Optional.of(user);
        }catch (PersistenceException e) {
            throw new AppServerException("Database Error", "Failed to insert user "+ e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT c FROM User c", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        em.remove(user);
    }

    @Transactional
    @Override
    public User update(User User) {
        return em.merge(User);
    }


    @Override
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


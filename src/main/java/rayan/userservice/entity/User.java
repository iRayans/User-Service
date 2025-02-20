package rayan.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import rayan.userservice.core.enums.RoleType;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true, updatable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}

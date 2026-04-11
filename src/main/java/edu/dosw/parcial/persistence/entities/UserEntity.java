package edu.dosw.parcial.persistence.entities;

import edu.dosw.parcial.core.models.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email")
        }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    /*
     * unique = true → índice de unicidad a nivel BD.
     * El validator de negocio hace la comprobación previa
     * para retornar un 409 controlado antes de llegar a la BD.
     */
    @Column(name = "email", nullable = false,
            unique = true, length = 150)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    public UserEntity() {}

    public UserEntity(Long id, String fullName, String email,
                      String password, UserRole role) {
        this.id       = id;
        this.fullName = fullName;
        this.email    = email;
        this.password = password;
        this.role     = role;
    }

    public Long     getId()               { return id; }
    public void     setId(Long v)         { this.id = v; }

    public String   getFullName()         { return fullName; }
    public void     setFullName(String v) { this.fullName = v; }

    public String   getEmail()            { return email; }
    public void     setEmail(String v)    { this.email = v; }

    public String   getPassword()         { return password; }
    public void     setPassword(String v) { this.password = v; }

    public UserRole getRole()             { return role; }
    public void     setRole(UserRole v)   { this.role = v; }
}

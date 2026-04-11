package edu.dosw.parcial.core.models;

import edu.dosw.parcial.core.models.enums.UserRole;

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private UserRole role;

    public User() {}

    public User(Long id, String fullName, String email, String password, UserRole role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}

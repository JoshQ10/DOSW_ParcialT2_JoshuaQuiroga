package edu.dosw.parcial.controller.mappers;

import edu.dosw.parcial.core.models.User;
import edu.dosw.parcial.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        if (user == null) return null;

        UserEntity e = new UserEntity();
        e.setId(user.getId());
        e.setFullName(user.getFullName());
        e.setEmail(user.getEmail());
        e.setPassword(user.getPassword());
        e.setRole(user.getRole());
        return e;
    }

    public User toDomain(UserEntity e) {
        if (e == null) return null;

        return new User(
                e.getId(),
                e.getFullName(),
                e.getEmail(),
                e.getPassword(),
                e.getRole()
        );
    }
}

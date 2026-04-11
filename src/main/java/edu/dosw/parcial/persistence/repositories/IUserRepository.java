package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    // Usado en F-02 (login) y en UserValidator (unicidad)
    Optional<UserEntity> findByEmail(String email);

    // Más eficiente que findByEmail cuando solo necesitamos saber si existe
    boolean existsByEmail(String email);
}

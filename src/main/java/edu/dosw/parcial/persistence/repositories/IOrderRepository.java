package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.core.models.enums.OrderStatus;
import edu.dosw.parcial.persistence.entities.OrderEntity;
import edu.dosw.parcial.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    /*
     * Regla de negocio F-04: un usuario solo puede tener
     * UN pedido activo (CREADO) a la vez.
     */
    Optional<OrderEntity> findByUserAndStatus(UserEntity user,
                                              OrderStatus status);

    // Historial de pedidos del usuario (F-06)
    List<OrderEntity> findAllByUser(UserEntity user);
}

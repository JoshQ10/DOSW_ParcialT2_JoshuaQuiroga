package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.core.models.enums.ProductStatus;
import edu.dosw.parcial.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    // Usado en F-03: consulta por código QR escaneado
    Optional<ProductEntity> findByQrCode(String qrCode);

    // Usado en F-07: listar productos disponibles
    List<ProductEntity> findByStatus(ProductStatus status);
}

package edu.dosw.parcial.controller.mappers;

import edu.dosw.parcial.core.models.Product;
import edu.dosw.parcial.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public ProductEntity toEntity(Product p) {
        if (p == null) return null;

        ProductEntity e = new ProductEntity();
        e.setId(p.getId());
        e.setName(p.getName());
        e.setDescription(p.getDescription());
        e.setPrice(p.getPrice());
        e.setQrCode(p.getQrCode());
        e.setStock(p.getStock());
        e.setStatus(p.getStatus());
        return e;
    }

    public Product toDomain(ProductEntity e) {
        if (e == null) return null;

        return new Product(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getPrice(),
                e.getQrCode(),
                e.getStock(),
                e.getStatus()
        );
    }
}

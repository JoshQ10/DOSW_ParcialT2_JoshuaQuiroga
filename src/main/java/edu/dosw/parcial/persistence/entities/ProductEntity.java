package edu.dosw.parcial.persistence.entities;

import edu.dosw.parcial.core.models.enums.ProductStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "idx_product_qr_code", columnList = "qr_code")
        }
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "qr_code", nullable = false,
            unique = true, length = 100)
    private String qrCode;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ProductStatus status;

    public ProductEntity() {}

    public Long          getId()                  { return id; }
    public void          setId(Long v)            { this.id = v; }

    public String        getName()                { return name; }
    public void          setName(String v)        { this.name = v; }

    public String        getDescription()         { return description; }
    public void          setDescription(String v) { this.description = v; }

    public BigDecimal    getPrice()               { return price; }
    public void          setPrice(BigDecimal v)   { this.price = v; }

    public String        getQrCode()              { return qrCode; }
    public void          setQrCode(String v)      { this.qrCode = v; }

    public Integer       getStock()               { return stock; }
    public void          setStock(Integer v)      { this.stock = v; }

    public ProductStatus getStatus()              { return status; }
    public void          setStatus(ProductStatus v){ this.status = v; }
}

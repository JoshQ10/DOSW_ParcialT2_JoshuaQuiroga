package edu.dosw.parcial.core.models;

import edu.dosw.parcial.core.models.enums.ProductStatus;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String qrCode;
    private Integer stock;
    private ProductStatus status;

    public Product() {}

    public Product(Long id, String name, String description, BigDecimal price, String qrCode, Integer stock, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qrCode = qrCode;
        this.stock = stock;
        this.status = status;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}

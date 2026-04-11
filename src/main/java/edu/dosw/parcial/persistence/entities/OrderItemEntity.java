package edu.dosw.parcial.persistence.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // precio capturado en el instante del pedido (snapshot)
    @Column(name = "unit_price", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal unitPrice;

    public OrderItemEntity() {}

    public Long          getId()                   { return id; }
    public void          setId(Long v)             { this.id = v; }

    public OrderEntity   getOrder()                { return order; }
    public void          setOrder(OrderEntity v)   { this.order = v; }

    public ProductEntity getProduct()              { return product; }
    public void          setProduct(ProductEntity v){ this.product = v; }

    public Integer       getQuantity()             { return quantity; }
    public void          setQuantity(Integer v)    { this.quantity = v; }

    public BigDecimal    getUnitPrice()            { return unitPrice; }
    public void          setUnitPrice(BigDecimal v){ this.unitPrice = v; }
}

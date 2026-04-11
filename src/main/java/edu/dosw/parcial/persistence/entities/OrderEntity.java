package edu.dosw.parcial.persistence.entities;

import edu.dosw.parcial.core.models.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "orders",
        indexes = {
                /*
                 * Índice compuesto para la consulta más frecuente:
                 * "¿tiene este usuario un pedido en estado X?"
                 */
                @Index(name = "idx_order_user_status",
                        columnList = "user_id, status")
        }
)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(
            mappedBy     = "order",
            cascade      = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "total", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public OrderEntity() {}

    public Long                  getId()                    { return id; }
    public void                  setId(Long v)              { this.id = v; }

    public UserEntity            getUser()                  { return user; }
    public void                  setUser(UserEntity v)      { this.user = v; }

    public List<OrderItemEntity> getItems()                 { return items; }
    public void                  setItems(List<OrderItemEntity> v){ this.items = v; }

    public OrderStatus           getStatus()                { return status; }
    public void                  setStatus(OrderStatus v)   { this.status = v; }

    public BigDecimal            getTotal()                 { return total; }
    public void                  setTotal(BigDecimal v)     { this.total = v; }

    public LocalDateTime         getCreatedAt()             { return createdAt; }
    public void                  setCreatedAt(LocalDateTime v){ this.createdAt = v; }
}

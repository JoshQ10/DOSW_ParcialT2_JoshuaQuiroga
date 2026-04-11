package edu.dosw.parcial.core.models;

import edu.dosw.parcial.core.models.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<OrderItem> items;
    private OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdAt;

    public Order() {}

    public Order(Long id, User user, List<OrderItem> items, OrderStatus status, BigDecimal total, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.status = status;
        this.total = total;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

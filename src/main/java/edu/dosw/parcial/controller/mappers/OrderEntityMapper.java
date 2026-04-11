package edu.dosw.parcial.controller.mappers;

import edu.dosw.parcial.core.models.Order;
import edu.dosw.parcial.core.models.OrderItem;
import edu.dosw.parcial.persistence.entities.OrderEntity;
import edu.dosw.parcial.persistence.entities.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderEntityMapper {

    private final UserEntityMapper    userMapper;
    private final ProductEntityMapper productMapper;

    public OrderEntityMapper(UserEntityMapper userMapper,
                             ProductEntityMapper productMapper) {
        this.userMapper    = userMapper;
        this.productMapper = productMapper;
    }

    public OrderEntity toEntity(Order order) {
        if (order == null) return null;

        OrderEntity e = new OrderEntity();
        e.setId(order.getId());
        e.setUser(userMapper.toEntity(order.getUser()));
        e.setStatus(order.getStatus());
        e.setTotal(order.getTotal());
        e.setCreatedAt(order.getCreatedAt());

        if (order.getItems() != null) {
            List<OrderItemEntity> itemEntities = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemEntity ie = new OrderItemEntity();
                ie.setId(item.getId());
                ie.setProduct(productMapper.toEntity(item.getProduct()));
                ie.setQuantity(item.getQuantity());
                ie.setUnitPrice(item.getUnitPrice());
                ie.setOrder(e);            // FK inversa
                itemEntities.add(ie);
            }
            e.setItems(itemEntities);
        }
        return e;
    }

    public Order toDomain(OrderEntity e) {
        if (e == null) return null;

        List<OrderItem> items = new ArrayList<>();
        if (e.getItems() != null) {
            for (OrderItemEntity ie : e.getItems()) {
                items.add(new OrderItem(
                        ie.getId(),
                        productMapper.toDomain(ie.getProduct()),
                        ie.getQuantity(),
                        ie.getUnitPrice()
                ));
            }
        }

        return new Order(
                e.getId(),
                userMapper.toDomain(e.getUser()),
                items,
                e.getStatus(),
                e.getTotal(),
                e.getCreatedAt()
        );
    }
}

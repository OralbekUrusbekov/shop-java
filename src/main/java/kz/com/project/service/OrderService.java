package kz.com.project.service;

import kz.com.project.Dto.OrderDTO;
import kz.com.project.model.Order;
import kz.com.project.model.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getMyOrders(String email);

    List<OrderDTO> getAllOrders();

    OrderDTO updateStatus(Long orderId, OrderStatus status);

    Order getEntity(Long id);
}

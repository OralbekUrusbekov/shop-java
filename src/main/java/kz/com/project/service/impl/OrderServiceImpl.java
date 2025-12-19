package kz.com.project.service.impl;

import kz.com.project.Dto.OrderDTO;
import kz.com.project.mapper.OrderMapper;
import kz.com.project.model.Order;
import kz.com.project.model.OrderStatus;
import kz.com.project.model.User;
import kz.com.project.repository.OrderRepository;
import kz.com.project.repository.UserRepository;
import kz.com.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getMyOrders(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        return orderMapper.toDtoList(orderRepository.findAllByUser(user));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDTO updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public Order getEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

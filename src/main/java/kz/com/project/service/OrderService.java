package kz.com.project.service;

import kz.com.project.Dto.OrderDTO;
import kz.com.project.mapper.OrderMapper;
import kz.com.project.model.*;
import kz.com.project.repository.CatRepository;
import kz.com.project.repository.OrderRepository;
import kz.com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getMyOrders(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }


        return orderMapper.toDtoList(orderRepository.findAllByUser(user));
    }

    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    public OrderDTO updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public Order getEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

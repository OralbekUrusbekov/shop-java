package kz.com.project.service;

import kz.com.project.Dto.OrderDTO;
import kz.com.project.model.Cat;
import kz.com.project.model.Order;
import kz.com.project.model.User;
import kz.com.project.repository.CatRepository;
import kz.com.project.repository.OrderRepository;
import kz.com.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CatRepository catRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, CatRepository catRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.catRepository = catRepository;
        this.userRepository = userRepository;
    }

    public OrderDTO createOrder(Long userId, Long catId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Cat not found"));

        Order order = new Order();
        order.setUser(user);
        order.setCat(cat);

        orderRepository.save(order);

        return new OrderDTO(order.getId(), userId, catId, cat.getName(), cat.getPrice());
    }

    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(order -> new OrderDTO(order.getId(),
                        order.getUser().getId(),
                        order.getCat().getId(),
                        order.getCat().getName(),
                        order.getCat().getPrice()))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order.getId(),
                        order.getUser().getId(),
                        order.getCat().getId(),
                        order.getCat().getName(),
                        order.getCat().getPrice()))
                .collect(Collectors.toList());
    }
}


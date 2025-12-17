package kz.com.project.service;

import kz.com.project.Dto.CartDTO;
import kz.com.project.Dto.OrderDTO;
import kz.com.project.mapper.CartMapper;
import kz.com.project.mapper.OrderMapper;
import kz.com.project.model.*;
import kz.com.project.repository.CartRepository;
import kz.com.project.repository.CatRepository;
import kz.com.project.repository.OrderRepository;
import kz.com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final CatRepository catRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;



    public void addToCart(String email, Long catId) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Cart cart = cartRepository.findByUser(user)
                .orElse(new Cart(null, user, new ArrayList<>()));

        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Cat not found"));

        cart.getItems().stream()
                .filter(i -> i.getCat().getId().equals(catId))
                .findFirst()
                .ifPresentOrElse(
                        i -> i.setQuantity(i.getQuantity() + 1),
                        () -> cart.getItems().add(new CartItem(null, cart, cat, 1))
                );

        cartRepository.save(cart);
    }

    public CartDTO getCart(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Cart cart = cartRepository.findByUser(user)
                .orElse(new Cart(null, user, new ArrayList<>()));

        return cartMapper.toDto(cart);
    }

    public OrderDTO checkout(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart empty"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = cart.getItems().stream().map(ci ->
                new OrderItem(null, ci.getCat(), ci.getQuantity(),
                        ci.getCat().getPrice() * ci.getQuantity())
        ).toList();

        order.setItems(items);
        order.setTotalPrice(
                items.stream().mapToDouble(OrderItem::getPrice).sum()
        );

        Order saved = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderMapper.toDto(saved);
    }
}



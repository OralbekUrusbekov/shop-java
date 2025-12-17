package kz.com.project.repository;

import kz.com.project.model.Order;
import kz.com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    List<Order> findAllByUser(User user);
}

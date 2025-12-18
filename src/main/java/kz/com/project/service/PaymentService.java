package kz.com.project.service;

import kz.com.project.Dto.PaymentDTO;
import kz.com.project.mapper.PaymentMapper;
import kz.com.project.model.Order;
import kz.com.project.model.OrderStatus;
import kz.com.project.model.Payment;
import kz.com.project.repository.OrderRepository;
import kz.com.project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    public PaymentDTO pay(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new RuntimeException("Order already paid");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDateTime.now());

        order.setStatus(OrderStatus.PAID);

        orderRepository.save(order);
        return paymentMapper.toDto(paymentRepository.save(payment));
    }
}


package kz.com.project.service;

import kz.com.project.Dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO pay(Long orderId);
}
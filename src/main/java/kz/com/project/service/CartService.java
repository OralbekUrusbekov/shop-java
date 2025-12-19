package kz.com.project.service;

import kz.com.project.Dto.CartDTO;
import kz.com.project.Dto.OrderDTO;

public interface CartService {

    void addToCart(String email, Long catId);

    CartDTO getCart(String email);

    OrderDTO checkout(String email);
}

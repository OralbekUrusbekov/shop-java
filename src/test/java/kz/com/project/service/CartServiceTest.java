package kz.com.project.service;

import kz.com.project.Dto.CartDTO;
import kz.com.project.Dto.OrderDTO;
import kz.com.project.model.Cat;
import kz.com.project.model.OrderStatus;
import kz.com.project.model.User;
import kz.com.project.repository.CatRepository;
import kz.com.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CatRepository catRepository;

    @Test
    void addToCart_and_getCart() {
        User user = new User();
        user.setEmail("cart@test.com");
        user.setPassword("123");
        userRepository.save(user);

        Cat cat = new Cat();
        cat.setName("Tom");
        cat.setBreed("British");
        cat.setAge(2);
        cat.setPrice(5000.0);
        catRepository.save(cat);

        cartService.addToCart("cart@test.com", cat.getId());

        CartDTO cartDTO = cartService.getCart("cart@test.com");

        Assertions.assertEquals(1, cartDTO.getItems().size());
        Assertions.assertEquals(1, cartDTO.getItems().get(0).getQuantity());
    }

    @Test
    void checkout_success() {
        User user = new User();
        user.setEmail("checkout@test.com");
        user.setPassword("123");
        userRepository.save(user);

        Cat cat = new Cat();
        cat.setName("Leo");
        cat.setBreed("Scottish");
        cat.setAge(1);
        cat.setPrice(7000.0);
        catRepository.save(cat);

        cartService.addToCart("checkout@test.com", cat.getId());

        OrderDTO orderDTO = cartService.checkout("checkout@test.com");

        Assertions.assertEquals(7000.0, orderDTO.getTotalPrice());
        Assertions.assertEquals(OrderStatus.NEW, orderDTO.getStatus());
    }
}

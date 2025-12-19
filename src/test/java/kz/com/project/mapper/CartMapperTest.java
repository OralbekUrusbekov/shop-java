package kz.com.project.mapper;

import kz.com.project.Dto.CartDTO;
import kz.com.project.model.Cart;
import kz.com.project.model.CartItem;
import kz.com.project.model.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartMapperTest {

    @Autowired
    private CartMapper mapper;

    @Test
    void testToDto() {
        Cat cat = new Cat();
        cat.setPrice(50.0);
        cat.setName("Murka");

        CartItem item = new CartItem();
        item.setCat(cat);
        item.setQuantity(2);

        Cart cart = new Cart();
        cart.setItems(Collections.singletonList(item));

        CartDTO dto = mapper.toDto(cart);
        assertEquals(100.0, dto.getTotalPrice());
        assertEquals(1, dto.getItems().size());
    }

    @Test
    void testToEntity() {
        CartDTO dto = new CartDTO();
        dto.setItems(Collections.emptyList());

        Cart entity = mapper.toEntity(dto);
        assertNotNull(entity);
    }

    @Test
    void testToDtoList() {
        Cat cat = new Cat();
        cat.setPrice(50.0);

        CartItem item = new CartItem();
        item.setCat(cat);
        item.setQuantity(2);

        Cart cart = new Cart();
        cart.setItems(Collections.singletonList(item));

        List<CartDTO> dtoList = mapper.toDtoList(Collections.singletonList(cart));
        assertEquals(1, dtoList.size());
        assertEquals(100.0, dtoList.get(0).getTotalPrice());
    }

    @Test
    void testToEntityList() {
        CartDTO dto = new CartDTO();
        List<Cart> entityList = mapper.toEntityList(Collections.singletonList(dto));
        assertEquals(1, entityList.size());
    }
}

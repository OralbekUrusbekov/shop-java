package kz.com.project.mapper;

import kz.com.project.Dto.CartItemDTO;
import kz.com.project.model.CartItem;
import kz.com.project.model.Cat;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartItemMapperTest {

    private final CartItemMapper mapper = Mappers.getMapper(CartItemMapper.class);

    @Test
    void testToDto() {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Murka");
        cat.setPrice(100.0);

        CartItem item = new CartItem();
        item.setCat(cat);
        item.setQuantity(2);

        CartItemDTO dto = mapper.toDto(item);
        assertEquals(cat.getId(), dto.getCatId());
        assertEquals(cat.getName(), dto.getCatName());
        assertEquals(200.0, dto.getPrice());
    }

    @Test
    void testToEntity() {
        CartItemDTO dto = new CartItemDTO();
        dto.setCatId(2L);
        dto.setCatName("Murka");
        dto.setQuantity(3);

        CartItem entity = mapper.toEntity(dto);
        assertEquals(dto.getCatId(), entity.getCat().getId());
        assertEquals(dto.getQuantity(), entity.getQuantity());
    }

    @Test
    void testToDtoList() {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Murka");
        cat.setPrice(100.0);
        CartItem item = new CartItem();
        item.setCat(cat);

        List<CartItemDTO> dtoList = mapper.toDtoList(Collections.singletonList(item));
        assertEquals(1, dtoList.size());
        assertEquals(cat.getId(), dtoList.get(0).getCatId());
    }

    @Test
    void testToEntityList() {
        CartItemDTO dto = new CartItemDTO();
        dto.setCatId(4L);

        List<CartItem> entityList = mapper.toEntityList(Collections.singletonList(dto));
        assertEquals(1, entityList.size());
        assertEquals(dto.getCatId(), entityList.get(0).getCat().getId());
    }
}

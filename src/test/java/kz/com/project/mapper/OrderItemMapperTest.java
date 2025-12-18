package kz.com.project.mapper;

import kz.com.project.Dto.OrderItemDTO;
import kz.com.project.model.Cat;
import kz.com.project.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderItemMapperTest {

    private final OrderItemMapper mapper = Mappers.getMapper(OrderItemMapper.class);

    @Test
    void testToDto() {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Murka");

        OrderItem item = new OrderItem();
        item.setCat(cat);
        item.setQuantity(5);

        OrderItemDTO dto = mapper.toDto(item);
        assertEquals(cat.getId(), dto.getCatId());
        assertEquals(cat.getName(), dto.getCatName());
    }

    @Test
    void testToEntity() {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setCatId(2L);
        dto.setQuantity(3);

        OrderItem entity = mapper.toEntity(dto);
        assertEquals(dto.getCatId(), entity.getCat().getId());
        assertEquals(dto.getQuantity(), entity.getQuantity());
    }

    @Test
    void testToDtoList() {
        Cat cat = new Cat();
        OrderItem item = new OrderItem();
        item.setCat(cat);

        List<OrderItemDTO> dtoList = mapper.toDtoList(Collections.singletonList(item));
        assertEquals(1, dtoList.size());
    }

    @Test
    void testToEntityList() {
        OrderItemDTO dto = new OrderItemDTO();
        List<OrderItem> entityList = mapper.toEntityList(Collections.singletonList(dto));
        assertEquals(1, entityList.size());
    }
}

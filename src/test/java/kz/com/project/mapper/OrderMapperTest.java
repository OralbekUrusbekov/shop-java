package kz.com.project.mapper;

import kz.com.project.Dto.OrderDTO;
import kz.com.project.model.Cat;
import kz.com.project.model.Order;
import kz.com.project.model.OrderItem;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class OrderMapperTest {

    @Autowired
    private OrderMapper mapper;

    @Test
    void testToDto() {
        OrderItem item = new OrderItem();
        item.setCat(new Cat());

        Order order = new Order();
        order.setItems(Collections.singletonList(item));
        order.setId(1L);

        OrderDTO dto = mapper.toDto(order);
        assertEquals(order.getId(), dto.getOrderId());
        assertEquals(1, dto.getItems().size());
    }

    @Test
    void testToEntity() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(2L);

        Order entity = mapper.toEntity(dto);
        assertEquals(dto.getOrderId(), entity.getId());
    }

    @Test
    void testToDtoList() {
        Order order = new Order();
        List<OrderDTO> dtoList = mapper.toDtoList(Collections.singletonList(order));
        assertEquals(1, dtoList.size());
    }

    @Test
    void testToEntityList() {
        OrderDTO dto = new OrderDTO();
        List<Order> entityList = mapper.toEntityList(Collections.singletonList(dto));
        assertEquals(1, entityList.size());
    }
}

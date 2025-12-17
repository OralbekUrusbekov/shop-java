package kz.com.project.mapper;

import kz.com.project.Dto.OrderItemDTO;
import kz.com.project.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "cat.id", target = "catId")
    @Mapping(source = "cat.name", target = "catName")
    OrderItemDTO toDto(OrderItem item);

    List<OrderItemDTO> toDtoList(List<OrderItem> items);
}

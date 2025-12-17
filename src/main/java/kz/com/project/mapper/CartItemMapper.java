package kz.com.project.mapper;

import kz.com.project.Dto.CartItemDTO;
import kz.com.project.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "cat.id", target = "catId")
    @Mapping(source = "cat.name", target = "catName")
    @Mapping(expression = "java(item.getCat().getPrice() * item.getQuantity())", target = "price")
    CartItemDTO toDto(CartItem item);

    List<CartItemDTO> toDtoList(List<CartItem> items);
}
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

    @Mapping(source = "catId", target = "cat.id")
    CartItem toEntity(CartItemDTO dto);

    List<CartItemDTO> toDtoList(List<CartItem> items);
    List<CartItem> toEntityList(List<CartItemDTO> dtos);
}

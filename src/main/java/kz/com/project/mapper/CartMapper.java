package kz.com.project.mapper;

import kz.com.project.Dto.CartDTO;
import kz.com.project.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = CartItemMapper.class
)
public interface CartMapper {

    @Mapping(target = "totalPrice",
            expression = "java(cart.getItems().stream().mapToDouble(i -> i.getCat().getPrice() * i.getQuantity()).sum())")
    CartDTO toDto(Cart cart);

    Cart toEntity(CartDTO dto);

    List<CartDTO> toDtoList(List<Cart> carts);
    List<Cart> toEntityList(List<CartDTO> dtos);
}

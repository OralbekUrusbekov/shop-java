package kz.com.project.mapper;

import kz.com.project.Dto.PaymentDTO;
import kz.com.project.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "id", target = "paymentId")
    PaymentDTO toDto(Payment payment);
}


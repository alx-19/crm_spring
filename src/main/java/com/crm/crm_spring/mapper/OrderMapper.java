package com.crm.crm_spring.mapper;

import com.crm.crm_spring.api.dto.OrderDto;
import com.crm.crm_spring.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customer.id" , target = "customerId")
    @Mapping(source = "house.id" , target = "productId")
    @Mapping(source = "dealer.id", target = "userId")
    OrderDto mapOrderToOrderDto(Order order);

    @Mapping(source = "customerId" , target = "customer.id")
    @Mapping(source = "productId" , target = "house.id")
    @Mapping(source = "userId", target = "dealer.id")
    Order mapOrderDtoToOrder(OrderDto orderDto);
}

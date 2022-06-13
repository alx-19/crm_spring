package com.crm.crm_spring.mapper;

import com.crm.crm_spring.api.dto.OrderDto;
import com.crm.crm_spring.model.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto mapOrderToOrderDto(Order order);

    Order mapOrderDtoToOrder(OrderDto orderDto);
}

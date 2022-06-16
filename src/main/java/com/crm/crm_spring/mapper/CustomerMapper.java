package com.crm.crm_spring.mapper;

import com.crm.crm_spring.api.dto.CustomerDto;
import com.crm.crm_spring.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    @Mapping(source = "dealer.id", target = "dealerId")
    CustomerDto mapCustomerToCustomerDto(Customer customer);

    @Mapping(source = "dealerId", target = "dealer.id")
    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);
}

package com.crm.crm_spring.mapper;

import com.crm.crm_spring.api.dto.CustomerDto;
import com.crm.crm_spring.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;



@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerDto mapCustomerToCustomerDto(Customer customer);

    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);
}

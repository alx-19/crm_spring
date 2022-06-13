package com.crm.crm_spring.mapper;

import com.crm.crm_spring.api.dto.ProductDto;
import com.crm.crm_spring.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductDto mapProductToProductDto(Product product);

    Product mapProductDtoToProduct(ProductDto productDto);
}

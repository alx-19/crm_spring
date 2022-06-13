package com.crm.crm_spring.mapper;


import com.crm.crm_spring.api.dto.UserDto;
import com.crm.crm_spring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto mapToDto(User user);

    User mapToModel(UserDto userDto);
}

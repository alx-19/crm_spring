package com.crm.crm_spring.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    private Integer id;
    private String lastname;
    private String firstname;
    private Integer streetNumber;
    private String streetName;
    private Integer zipcode;
    private String city;
    private String mail;
    private String phone;
    private String mobile;
    private String notes;
    private Boolean active;
}

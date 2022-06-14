package com.crm.crm_spring.api.v1;

import com.crm.crm_spring.api.dto.CustomerDto;
import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.mapper.CustomerMapper;
import com.crm.crm_spring.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin(value = {"*"}, allowedHeaders = {"*"})
@RequiredArgsConstructor
public class CustomerApi {

    Logger log = LoggerFactory.getLogger(CustomerApi.class);
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Return the list of all customers ordered by lastname ascending.")
    public ResponseEntity<List<CustomerDto>> getAll() {
        log.info("Retrieving customers...");

        return ResponseEntity.ok(
                this.customerService.getAll().stream()
                        .map(this.customerMapper::mapCustomerToCustomerDto)
                        .toList()
        );
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Trying to retrieve a customer from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the customer found the given ID"),
            @ApiResponse(responseCode = "404", description = "No customer found the given ID")
    })
    public ResponseEntity<CustomerDto> getById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(
                    this.customerMapper
                            .mapCustomerToCustomerDto(this.customerService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a customer")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody final CustomerDto customerDto) {
        log.debug("Attempting to create customer with lastname {}", customerDto.getLastname());

        CustomerDto customerDtoResponse =
                this.customerMapper.mapCustomerToCustomerDto(
                        this.customerService.create(
                                this.customerMapper.mapCustomerDtoToCustomer(customerDto)
                        ));

        return ResponseEntity
                .created(URI.create("/v1/customers/" + customerDtoResponse.getId()))
                .body(customerDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a customer for the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "No customer found the given ID"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the customer for the given ID")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable final Integer id) {
        log.debug("Attemtping to delete a customer with id {}", id);
        try {
            this.customerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> updateCustomer(@PathVariable final Integer id, @RequestBody CustomerDto customerDto) {
        try {
            log.debug("Updating customer {}", customerDto.getId());
            customerDto.setId(id);
            this.customerService.update(customerMapper.mapCustomerDtoToCustomer(customerDto));
            log.debug("Successfully updated customer {}", customerDto.getId());

            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}

package com.crm.crm_spring.api.v1;

import com.crm.crm_spring.api.dto.OrderDto;
import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.mapper.OrderMapper;
import com.crm.crm_spring.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(value = {"*"}, allowedHeaders = {"*"})
@RequiredArgsConstructor
public class OrderApi {

    Logger log = LoggerFactory.getLogger(OrderApi.class);

    public static final String ORDER_NOT_FOUND = "Order Not Found";
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all orders", responses = @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))))
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity
                .ok(orderService.getAll().stream()
                        .map(this.orderMapper::mapOrderToOrderDto)
//                        .map(order -> this.orderMapper.mapOrderToOrderDto(order))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return an order", responses = @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))))
    public ResponseEntity<OrderDto> getById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(orderMapper.mapOrderToOrderDto(orderService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Create order", responses = @ApiResponse(responseCode = "201", description = "Created"))
    public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderDto orderDto) {
        log.debug("Attempting to create order with name {}", orderDto.getName());
        OrderDto newOrder = orderMapper
                .mapOrderToOrderDto(orderService
                        .create(orderMapper.mapOrderDtoToOrder(orderDto)));
        return ResponseEntity.created(URI.create("/v1/orders/" + newOrder.getId())).body(newOrder);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete order", responses = @ApiResponse(responseCode = "204", description = "No Content"))
    public ResponseEntity<Void> deleteOrder(@PathVariable final Integer id) {
        try {
            log.debug("Preparing to delete order with id [{}]", id);
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update order", responses = @ApiResponse(responseCode = "204", description = "No Content"))
    public ResponseEntity<Void> updateOrder(@PathVariable final Integer id, @RequestBody OrderDto orderDto) {
        try {
            log.debug("Updating order {}", id);
            orderDto.setId(id);
            orderService.update(orderMapper.mapOrderDtoToOrder(orderDto));
            log.debug("Successfully updated order {}", id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
        }
    }
}

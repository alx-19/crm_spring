package com.crm.crm_spring.api.v1;

import com.crm.crm_spring.api.dto.ProductDto;
import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.mapper.ProductMapper;
import com.crm.crm_spring.service.ProductService;
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
@RequestMapping("api/v1/products")
@CrossOrigin(value = {"*"}, allowedHeaders = {"*"})
@RequiredArgsConstructor
public class ProductApi {

    Logger log = LoggerFactory.getLogger(ProductApi.class);

    public static final String PRODUCT_NOT_FOUND = "Order Not Found";
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all products", responses = @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))))
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity
                .ok(productService.getAll().stream()
                        .map(this.productMapper::mapProductToProductDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return an product", responses = @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))))
    public ResponseEntity<ProductDto> getById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(productMapper.mapProductToProductDto(productService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Create product", responses = @ApiResponse(responseCode = "201", description = "Created"))
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        log.debug("Attempting to create product with name {}", productDto.getName());
        ProductDto newProduct = productMapper
                .mapProductToProductDto(productService
                        .create(productMapper.mapProductDtoToProduct(productDto)));
        return ResponseEntity.created(URI.create("/v1/products/" + newProduct.getId())).body(newProduct);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete product", responses = @ApiResponse(responseCode = "204", description = "No Content"))
    public ResponseEntity<Void> deleteProduct(@PathVariable final Integer id) {
        try {
            log.debug("Preparing to delete product with id [{}]", id);
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update product", responses = @ApiResponse(responseCode = "204", description = "No Content"))
    public ResponseEntity<Void> updateProduct(@PathVariable final Integer id, @RequestBody ProductDto productDto) {
        try {
            log.debug("Updating product {}", id);
            productDto.setId(id);
            productService.update(productMapper.mapProductDtoToProduct(productDto));
            log.debug("Successfully updated product {}", id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
        }
    }
}

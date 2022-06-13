package com.crm.crm_spring.api.v1;

import com.crm.crm_spring.api.dto.UserDto;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.mapper.UserMapper;
import com.crm.crm_spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(value = {"*"}, allowedHeaders = {"*"})
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all users")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(userMapper::mapToDto)
                        .toList()
        );
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return a user by its id")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(userMapper.mapToDto(userService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create a user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userCreated = userMapper.mapToDto(userService.create(userMapper.mapToModel(userDto)));
        return ResponseEntity.created(URI.create("/v1/users/" + userCreated.getId()))
                .body(userCreated);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update user")
    public ResponseEntity<UserDto> updateUSer(@RequestBody UserDto userDto, @PathVariable final Integer id) {
        try {
            userDto.setId(id);
            UserDto updatedUser = userMapper.mapToDto(userService.update(userMapper.mapToModel(userDto)));
            return ResponseEntity.ok(updatedUser);
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @GetMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return a user by its username and password")
    public ResponseEntity<UserDto> login(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(
                    userMapper.mapToDto(userService.getUserByUsernameAndPassword(username, password))
            );
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ure.getMessage());
        }
    }

}

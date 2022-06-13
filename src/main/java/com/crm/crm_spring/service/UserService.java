package com.crm.crm_spring.service;

import com.crm.crm_spring.model.User;

import java.util.List;

public interface UserService {

    /**
     * Get all users
     * @return all users
     */
    List<User> getAll();

    /**
     * Get user by id
     * @param id the id
     * @return user with the given id
     */
    User getById(Integer id);

    /**
     * Create user
     * @param user the user to create
     * @return the created user
     */
    User create(User user);

    /**
     * Delete user
     * @param id the user id
     */
    void delete(Integer id);

    /**
     * Update user
     * @param user the user
     * @return the updated user
     */
    User update(User user);

    /**
     * Get user by username
     * @param username the username
     * @return user with the given username
     */
    User getByUsername(String username);

    /**
     * Get user by username and password
     * @param username the username
     * @param password the password
     * @return user with the given password and username
     */
    User getUserByUsernameAndPassword(String username, String password);



}

package com.crm.crm_spring.service.impl;

import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.User;
import com.crm.crm_spring.repository.UserRepository;
import com.crm.crm_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by("username").ascending());
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("Unknown user."));
    }

    @Override
    public User create(User user) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User userToDelete = this.getById(id);
        if(this.canDeleteUser(userToDelete)){
            this.userRepository.delete(userToDelete);
        }else {
            throw new NotAllowedToDeleteException("The giver user still active");
        }
    }
    private boolean canDeleteUser(User user) {
        return (null == user.getOrder() || user.getOrder().isEmpty());
    }


    @Override
    public User update(User user) {
        User existingUser = this.getById(user.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setLastname(user.getLastname());
        existingUser.setMail(user.getMail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        existingUser.setOrder(user.getOrder());
        // Chiffrer le MDP reçu
        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        existingUser.setPassword(passwordEncoded);
        return userRepository.save(existingUser);
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UnknownResourceException("No user found for this username."));
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsername(username).get();
        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return user;
        }
        throw new UnknownResourceException("No user found for the given user/password");
    }
}

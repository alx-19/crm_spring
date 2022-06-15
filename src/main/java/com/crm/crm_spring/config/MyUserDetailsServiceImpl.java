package com.crm.crm_spring.config;


import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.User;
import com.crm.crm_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User existingUser = this.userService.getByUsername(username);
            // si le user existe en bdd on continu
            return new MyUserDetails(existingUser);
        } catch (UnknownResourceException ure) {
            // cas contraire message d'erreur
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
    }
}
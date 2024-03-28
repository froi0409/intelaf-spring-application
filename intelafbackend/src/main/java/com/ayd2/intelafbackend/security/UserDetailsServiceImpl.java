package com.ayd2.intelafbackend.security;


import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userOpt.get().getUsername())
                    .password(userOpt.get().getPassword())
                    //.roles(userOpt.getRoles()) // pending give role
                    .build();

            return userDetails;
        }

        throw new UsernameNotFoundException("User not found");

    }

}

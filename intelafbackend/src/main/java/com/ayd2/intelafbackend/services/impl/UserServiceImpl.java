package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.user.UserRequestDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.exceptions.NotFoundException;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO:: new)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(UserRequestDTO userRequestDTO) throws NotAcceptableException {

        Optional<User> foundUser = userRepository.findByNitDPIUsername(userRequestDTO.getNit(), userRequestDTO.getDpi(), userRequestDTO.getUsername());

        if (foundUser.isPresent()) {
            throw new NotAcceptableException("NIT, DPI OR USERNAME REPETIDO");
        }

        User newUser = new User();
        newUser.setNit(userRequestDTO.getNit());
        newUser.setName(userRequestDTO.getName());
        newUser.setPhone(userRequestDTO.getPhone());
        newUser.setDpi(userRequestDTO.getDpi());
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setAddress(userRequestDTO.getAddress());
        newUser.setPassword(userRequestDTO.getPassword());
        newUser.setUsername(userRequestDTO.getUsername());

        newUser  = userRepository.save(newUser);
        return newUser;
    }


}

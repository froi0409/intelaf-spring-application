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

    @Override
    public User editUser(UserRequestDTO userRequestDTO) throws NotAcceptableException {
        // find el user por su ID
        Optional<User> optionalUser = userRepository.findByNit(userRequestDTO.getNit());

        // verify si el user exist
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        //Get the user
        User existingUser = optionalUser.get();

        // Verificar si se están modificando campos sensibles (NIT, DPI o Username)
        if (!existingUser.getNit().equals(userRequestDTO.getNit()) ||
                !existingUser.getDpi().equals(userRequestDTO.getDpi()) ||
                !existingUser.getUsername().equals(userRequestDTO.getUsername())) {
            // Verificar si ya existe un usuario con los mismos valores de NIT, DPI o Username
            Optional<User> foundUser = userRepository.findDPIUsernameDiferentByNit(userRequestDTO.getNit(), userRequestDTO.getDpi(), userRequestDTO.getUsername());
            if (foundUser.isPresent()) {
                throw new NotAcceptableException("NIT, DPI, or Username already exists");
            }
        }

        // Actualizar los campos del usuario con los nuevos valores proporcionados
        existingUser.setNit(userRequestDTO.getNit());
        existingUser.setName(userRequestDTO.getName());
        existingUser.setPhone(userRequestDTO.getPhone());
        existingUser.setDpi(userRequestDTO.getDpi());
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setAddress(userRequestDTO.getAddress());
        existingUser.setPassword(userRequestDTO.getPassword());
        existingUser.setUsername(userRequestDTO.getUsername());

        // Guardar y devolver el usuario actualizado
        return userRepository.save(existingUser);

    }


}

package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.user.UserRequestDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;


import java.util.List;

public interface UserService {

    List<UserResponseDTO> findAll();

    User createUser(UserRequestDTO userRequestDTO) throws NotAcceptableException;

    User editUser(UserRequestDTO userRequestDTO) throws NotAcceptableException;

}

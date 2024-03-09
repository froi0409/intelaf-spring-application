package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
}

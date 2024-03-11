package com.ayd2.intelafbackend.controllers;


import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.services.StoreService;
import com.ayd2.intelafbackend.services.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/store")
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody CreateStoreRequestDTO newStore) throws DuplicatedEntityException {
        StoreResponseDTO responseDTO = storeService.createStore(newStore);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<StoreResponseDTO>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }

}

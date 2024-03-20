package com.ayd2.intelafbackend.controllers;


import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/store")
//@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping
    public ResponseEntity<StoreResponseDTO> editDriver(@RequestBody EditStoreRequestDTO storeToUpdate) throws EntityNotFoundException, DuplicatedEntityException {
        StoreResponseDTO responseDTO = storeService.editStore(storeToUpdate);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "/{idStore}")
    public ResponseEntity<StoreResponseDTO> findStoreById(@PathVariable String idStore) throws EntityNotFoundException {
        return ResponseEntity.ok(storeService.findById(idStore));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<StoreResponseDTO>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }

}

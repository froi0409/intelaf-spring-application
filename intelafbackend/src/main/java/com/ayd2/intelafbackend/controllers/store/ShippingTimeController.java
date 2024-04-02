package com.ayd2.intelafbackend.controllers.store;

import com.ayd2.intelafbackend.dto.store.CreateShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.ShippingTimeResponseDTO;
import com.ayd2.intelafbackend.entities.store.ShippingTime;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.services.ShippingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shippingtime")
@CrossOrigin(origins = "http://localhost:3000")
public class ShippingTimeController {

    private ShippingTimeService shippingTimeService;

    @Autowired
    public ShippingTimeController(ShippingTimeService shippingTimeService) {
        this.shippingTimeService = shippingTimeService;
    }

    @GetMapping(path = "/getAll")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ShippingTimeResponseDTO>> findAll() {
        return ResponseEntity.ok(shippingTimeService.findAll());
    }

    @GetMapping(path = "/getByOrigin/{idStore}")
    public ResponseEntity<List<ShippingTimeResponseDTO>> findByOrigin(@PathVariable String idStore) {
        return ResponseEntity.ok(shippingTimeService.findAllByOrigin(idStore));
    }

    @GetMapping(path = "/getByDestiny/{idStore}")
    public ResponseEntity<List<ShippingTimeResponseDTO>> findByDestiny(@PathVariable String idStore) {
        return ResponseEntity.ok(shippingTimeService.findAllByDestiny(idStore));
    }

    @PostMapping
    public ResponseEntity<ShippingTimeResponseDTO> createShippingTime(@RequestBody CreateShippingTimeRequestDTO newShippingTimne) throws EntityNotFoundException {
        ShippingTimeResponseDTO responseDTO = shippingTimeService.createShippingTime(newShippingTimne);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ShippingTimeResponseDTO> editShippingTime(@RequestBody EditShippingTimeRequestDTO shippingTimeToEdit) throws EntityNotFoundException {
        ShippingTimeResponseDTO responseDTO = shippingTimeService.editShippingTime(shippingTimeToEdit);

        return ResponseEntity.ok(responseDTO);
    }

}

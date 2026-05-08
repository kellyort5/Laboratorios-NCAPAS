package com.example.ejepropuesto.controller;

import com.example.ejepropuesto.domain.entity.Pirate;
import com.example.ejepropuesto.services.PirateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pirates")
@RequiredArgsConstructor
public class PirateController {

    private final PirateService pirateService;

    @PostMapping
    public ResponseEntity<Pirate> createPirate(@RequestBody Pirate pirate) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pirateService.createPirate(pirate));
    }

    @GetMapping
    public ResponseEntity<List<Pirate>> getAllPirates() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pirateService.getAllPirates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pirate> getPirateById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pirateService.getPirateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pirate> updatePirate(
            @PathVariable UUID id,
            @RequestBody Pirate pirate
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pirateService.updatePirate(id, pirate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePirate(@PathVariable UUID id) {
        pirateService.deletePirate(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}


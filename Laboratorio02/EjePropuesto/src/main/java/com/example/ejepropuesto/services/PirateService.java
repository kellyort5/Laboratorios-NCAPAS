package com.example.ejepropuesto.services;

import com.example.ejepropuesto.domain.entity.Pirate;

import java.util.List;
import java.util.UUID;

public interface PirateService {

    Pirate createPirate(Pirate pirate);

    List<Pirate> getAllPirates();

    Pirate getPirateById(UUID id);

    Pirate updatePirate(UUID id, Pirate pirate);

    void deletePirate(UUID id);
}

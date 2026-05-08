package com.example.ejepropuesto.services.impl;

import com.example.ejepropuesto.domain.entity.Pirate;
import com.example.ejepropuesto.repository.PirateRepository;
import com.example.ejepropuesto.services.PirateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PirateServiceImpl implements PirateService {

    private final PirateRepository pirateRepository;

    @Override
    public Pirate createPirate(Pirate pirate) {
        return pirateRepository.save(pirate);
    }

    @Override
    public List<Pirate> getAllPirates() {
        return pirateRepository.findAll();
    }

    @Override
    public Pirate getPirateById(UUID id) {
        return pirateRepository.findById(id).orElse(null);
    }

    @Override
    public Pirate updatePirate(UUID id, Pirate pirate) {
        Pirate existPirate = pirateRepository.findById(id).orElse(null);

        existPirate.setName(pirate.getName());
        existPirate.setBounty(pirate.getBounty());
        existPirate.setCrew(pirate.getCrew());
        existPirate.setIsAlive(pirate.getIsAlive());

        return pirateRepository.save(existPirate);
    }

    @Override
    public void deletePirate(UUID id) {
        pirateRepository.deleteById(id);
    }
}

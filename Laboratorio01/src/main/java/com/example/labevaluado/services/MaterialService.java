package com.example.labevaluado.services;

import com.example.labevaluado.domain.entity.Material;
import com.example.labevaluado.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<Material> obtenerTodosOrdenadosPorPrecioDesc() {
        return materialRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Material::getPrecio).reversed())
                .toList();
    }

    public Material obtenerMaterialMasCaro() {
        return materialRepository.findAll()
                .stream()
                .max(Comparator.comparing(Material::getPrecio))
                .orElse(null);
    }

    public List<Material> obtenerLegendarios() {
        return materialRepository.findAll()
                .stream()
                .filter(material -> material.getRareza().equalsIgnoreCase("Legendario"))
                .toList();
    }

    public List<String> obtenerUbicacionesSinRepetir() {
        return materialRepository.findAll()
                .stream()
                .map(Material::getUbicacion)
                .distinct()
                .toList();
    }
}


package com.example.labevaluado.repository;

import com.example.labevaluado.common.MaterialList;
import com.example.labevaluado.domain.entity.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor

public class MaterialRepository {
    private final MaterialList productList;

    public List<Material> findAll() {
        return productList.getMaterials();
    }
}

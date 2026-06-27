package com.example.laboratorio03.services.impl;

import com.example.laboratorio03.common.mappers.SpecimenMapper;
import com.example.laboratorio03.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio03.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio03.domain.dto.response.PageableResponse;
import com.example.laboratorio03.domain.dto.response.specimen.SpecimenResponse;
import com.example.laboratorio03.domain.entities.Specimen;
import com.example.laboratorio03.exceptions.ResourceNotFoundException;
import com.example.laboratorio03.repositories.SpecimenRepository;
import com.example.laboratorio03.services.SpecimenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {

    private final SpecimenRepository specimenRepository;

    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {

        return specimenMapper.toDto(
                specimenRepository.save(
                        specimenMapper.toEntityCreate(request)
                )
        );
    }

    @Override
    public PageableResponse getAllSpecimens(
            int page,
            int size,
            String sortBy,
            String sortOrder
    ) {

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Specimen> specimens =
                specimenRepository.findAll(pageable);

        Page<SpecimenResponse> responsePage =
                specimenMapper.toDtoPage(specimens);

        return PageableResponse.builder()
                .page(responsePage.getNumber())
                .size(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .content(responsePage.getContent())
                .build();
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {

        return specimenMapper.toDto(
                specimenRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Specimen not found in Hyrule Records"
                                )
                        )
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(
            UUID id,
            UpdateSpecimenRequest request
    ) {

        this.getSpecimenById(id);

        return specimenMapper.toDto(
                specimenRepository.save(
                        specimenMapper.toEntityUpdate(request, id)
                )
        );
    }

    @Override
    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {

        SpecimenResponse existSpecimen =
                this.getSpecimenById(id);

        specimenRepository.deleteById(id);

        return existSpecimen;
    }
}
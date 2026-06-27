package com.example.laboratorio03.services.impl;

import com.example.laboratorio03.common.mappers.SpecimenMapper;
import com.example.laboratorio03.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio03.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio03.domain.dto.response.specimen.SpecimenResponse;
import com.example.laboratorio03.domain.entities.Specimen;
import com.example.laboratorio03.exceptions.ResourceNotFoundException;
import com.example.laboratorio03.repositories.SpecimenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SpecimenServiceImplTest {

    @Mock
    private SpecimenRepository specimenRepository;

    @Mock
    private SpecimenMapper specimenMapper;

    @InjectMocks
    private SpecimenServiceImpl specimenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSpecimen_ShouldCreateSpecimenSuccessfully() {

        CreateSpecimenRequest request = CreateSpecimenRequest.builder()
                .name("Guardian")
                .region("Hyrule Field")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        Specimen specimen = Specimen.builder()
                .id(UUID.randomUUID())
                .name("Guardian")
                .region("Hyrule Field")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        SpecimenResponse response = SpecimenResponse.builder()
                .id(specimen.getId())
                .name(specimen.getName())
                .region(specimen.getRegion())
                .dangerLevel(specimen.getDangerLevel())
                .isFriendly(specimen.getIsFriendly())
                .build();

        when(specimenMapper.toEntityCreate(request))
                .thenReturn(specimen);

        when(specimenRepository.save(specimen))
                .thenReturn(specimen);

        when(specimenMapper.toDto(specimen))
                .thenReturn(response);

        SpecimenResponse result =
                specimenService.createSpecimen(request);

        assertEquals("Guardian", result.getName());

        verify(specimenMapper).toEntityCreate(request);
        verify(specimenRepository).save(specimen);
        verify(specimenMapper).toDto(specimen);
    }
    @Test
    void getSpecimenById_ShouldReturnSpecimenSuccessfully() {

        UUID id = UUID.randomUUID();

        Specimen specimen = Specimen.builder()
                .id(id)
                .name("Lynel")
                .region("Hebra")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        SpecimenResponse response = SpecimenResponse.builder()
                .id(id)
                .name("Lynel")
                .region("Hebra")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        when(specimenRepository.findById(id))
                .thenReturn(java.util.Optional.of(specimen));

        when(specimenMapper.toDto(specimen))
                .thenReturn(response);

        SpecimenResponse result =
                specimenService.getSpecimenById(id);

        assertEquals(id, result.getId());

        verify(specimenRepository).findById(id);
        verify(specimenMapper).toDto(specimen);
    }
    @Test
    void getSpecimenById_ShouldThrowExceptionWhenSpecimenDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(specimenRepository.findById(id))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> specimenService.getSpecimenById(id)
        );

        verify(specimenRepository).findById(id);
    }

    @Test
    void updateSpecimen_ShouldUpdateSpecimenSuccessfully() {

        UUID id = UUID.randomUUID();

        UpdateSpecimenRequest request = UpdateSpecimenRequest.builder()
                .name("Guardian Sky")
                .region("Sky Islands")
                .dangerLevel(9)
                .isFriendly(false)
                .build();

        Specimen specimen = Specimen.builder()
                .id(id)
                .name("Guardian")
                .region("Hyrule Field")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        Specimen updatedSpecimen = Specimen.builder()
                .id(id)
                .name("Guardian Sky")
                .region("Sky Islands")
                .dangerLevel(9)
                .isFriendly(false)
                .build();

        SpecimenResponse response = SpecimenResponse.builder()
                .id(id)
                .name("Guardian Sky")
                .region("Sky Islands")
                .dangerLevel(9)
                .isFriendly(false)
                .build();

        when(specimenRepository.findById(id))
                .thenReturn(java.util.Optional.of(specimen));

        when(specimenMapper.toDto(specimen))
                .thenReturn(
                        SpecimenResponse.builder()
                                .id(id)
                                .name("Guardian")
                                .region("Hyrule Field")
                                .dangerLevel(10)
                                .isFriendly(false)
                                .build()
                );

        when(specimenMapper.toEntityUpdate(request, id))
                .thenReturn(updatedSpecimen);

        when(specimenRepository.save(updatedSpecimen))
                .thenReturn(updatedSpecimen);

        when(specimenMapper.toDto(updatedSpecimen))
                .thenReturn(response);

        SpecimenResponse result =
                specimenService.updateSpecimen(id, request);

        assertEquals("Guardian Sky", result.getName());

        verify(specimenRepository).findById(id);
        verify(specimenMapper).toEntityUpdate(request, id);
        verify(specimenRepository).save(updatedSpecimen);
    }
    @Test
    void deleteSpecimen_ShouldDeleteSuccessfully() {

        UUID id = UUID.randomUUID();

        Specimen specimen = Specimen.builder()
                .id(id)
                .name("Guardian")
                .region("Hyrule Field")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        SpecimenResponse response = SpecimenResponse.builder()
                .id(id)
                .name("Guardian")
                .region("Hyrule Field")
                .dangerLevel(10)
                .isFriendly(false)
                .build();

        when(specimenRepository.findById(id))
                .thenReturn(java.util.Optional.of(specimen));

        when(specimenMapper.toDto(specimen))
                .thenReturn(response);

        SpecimenResponse result =
                specimenService.deleteSpecimen(id);

        assertEquals(id, result.getId());

        verify(specimenRepository).findById(id);
        verify(specimenRepository).deleteById(id);
    }

}
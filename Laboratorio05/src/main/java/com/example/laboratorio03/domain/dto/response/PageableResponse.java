package com.example.laboratorio03.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageableResponse {

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    private List<?> content;
}
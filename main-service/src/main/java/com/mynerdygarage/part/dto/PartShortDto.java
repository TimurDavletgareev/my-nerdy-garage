package com.mynerdygarage.part.dto;

import lombok.Data;

@Data
public class PartShortDto {

    private final Long id;

    private final String partNumber;

    private final String name;
}

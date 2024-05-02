package com.example.armstorage.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CreateItemRequest {

    @NotBlank(message = "should be not blank")
    private String name;

    private String measurement = "шт";

    @NotNull(message="should be not null")
    private Double price;

    @NotNull(message="should be not null")
    private Long categoryId;
}

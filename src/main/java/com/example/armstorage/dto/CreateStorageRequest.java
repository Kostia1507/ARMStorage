package com.example.armstorage.dto;

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
public class CreateStorageRequest {

    @NotBlank(message = "should be not blank")
    private String name;

    @NotNull(message="should be not null")
    private Long cellNumber;
}

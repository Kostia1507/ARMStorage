package com.example.armstorage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EditUserInStorageRequest {

    @NotNull(message = "should not be null")
    private Long userId;

    @NotNull(message = "should not be null")
    private Long storageId;


}
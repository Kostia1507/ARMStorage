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
public class AddItemToStorageRequest {

    @NotNull
    private Long storageId;

    @NotNull
    private Long itemId;

    @NotNull
    private Long count;

    @NotNull
    private Long cell;

    @NotNull
    private Double price;

}

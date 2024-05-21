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
public class MoveItemRequest {

    @NotNull
    private Long storageFromId;

    @NotNull
    private Long storageToId;

    @NotNull
    private Long itemId;

    @NotNull
    private Long count;

    @NotNull
    private Long cellFrom;

    @NotNull
    private Long cellTo;

}

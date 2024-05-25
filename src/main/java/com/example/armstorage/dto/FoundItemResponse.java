package com.example.armstorage.dto;

import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoundItemResponse {

    private Long cell;
    private Long count;
    private Double price;
    private ItemEntity item;
    private StorageEntity storageEntity;
}

package com.example.armstorage.dto;

import com.example.armstorage.entities.ItemEntity;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class CategoryWithItemsResponse {

   private Long id;
   private String name;
   private Set<ItemEntity> items;

}

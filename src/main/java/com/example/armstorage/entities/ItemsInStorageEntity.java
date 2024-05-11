package com.example.armstorage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "items_in_storage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ItemsInStorageEntityId.class)
public class ItemsInStorageEntity {

    @Id
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private ItemEntity item;

    @Id
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private StorageEntity storage;

    @Column(name = "count")
    private Long count;

    @Column(name = "cell")
    private Long cell;
}


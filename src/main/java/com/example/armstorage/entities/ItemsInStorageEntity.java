package com.example.armstorage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "items_in_storage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsInStorageEntity {

    @Id
    @ManyToOne()
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private ItemEntity item;

    @ManyToOne()
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private StorageEntity storage;

    @Column(name = "count")
    private Long count;

    @Column(name = "cell")
    private Long cell;
}
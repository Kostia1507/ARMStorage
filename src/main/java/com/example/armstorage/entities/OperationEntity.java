package com.example.armstorage.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "operations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "storage_from", referencedColumnName = "id")
    private StorageEntity storageFrom;

    @ManyToOne()
    @JoinColumn(name = "storage_to", referencedColumnName = "id")
    private StorageEntity storageTo;

    @ManyToOne()
    @JoinColumn(name = "item_manipulated", referencedColumnName = "item_id")
    private ItemEntity item;

    @Column(name = "items_count")
    private Long itemsCount;

    @Column(name = "cell_from")
    private Long cellFrom;

    @Column(name = "cell_to")
    private Long cellTo;

    @Column(name="description")
    private String descriptionl;

    @Column(name="date")
    private Date date;
}
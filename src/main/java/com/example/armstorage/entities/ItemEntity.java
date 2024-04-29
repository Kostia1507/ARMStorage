package com.example.armstorage.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy="item")
    private List<ItemsInStorageEntity> itemsInStorage;
}
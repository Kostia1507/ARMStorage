package com.example.armstorage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @JsonIgnore
    @OneToMany(mappedBy="item")
    private List<ItemsInStorageEntity> itemsInStorage;
}
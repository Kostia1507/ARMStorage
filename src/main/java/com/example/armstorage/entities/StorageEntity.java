package com.example.armstorage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "storages")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cell_numbers")
    private Long cell_numbers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_storages",
            joinColumns = @JoinColumn(name = "storage_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<ItemEntity> users;

    @OneToMany(mappedBy="storage")
    private List<ItemsInStorageEntity> itemsInStorage;
}
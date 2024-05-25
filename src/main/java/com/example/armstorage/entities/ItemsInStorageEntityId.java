package com.example.armstorage.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ItemsInStorageEntityId implements Serializable {

    private ItemEntity item;
    private StorageEntity storage;
    private Long cell;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemsInStorageEntity that = (ItemsInStorageEntity) o;
        return (Objects.equals(item.getId(), that.getItem().getId())) && (storage.getId().equals(that.getStorage().getId()));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

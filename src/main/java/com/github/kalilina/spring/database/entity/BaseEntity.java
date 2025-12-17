package com.github.kalilina.spring.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {

    void setId(T id);
    T getId();
}

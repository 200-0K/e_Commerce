package com.hyperz.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    public int id;

    @NonNull
    public String name;

    public String icon;
}

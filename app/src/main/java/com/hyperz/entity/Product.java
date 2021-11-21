package com.hyperz.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "categoryid"
        )
})
public class Product {
    @PrimaryKey
    public int id;

    @NonNull
    @ColumnInfo(name = "categoryid")
    public int categoryId;

    @NonNull
    public String title;

    @NonNull
    @ColumnInfo(name = "price", defaultValue = "0.0")
    public double price;

    @NonNull
    @ColumnInfo(name = "rate", defaultValue = "0.0")
    public float rate;

    public String description;

    public String image;
}

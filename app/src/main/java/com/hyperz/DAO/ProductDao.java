package com.hyperz.DAO;

import com.hyperz.entity.Product;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE categoryid = :categoryId")
    List<Product> getByCategory(int categoryId);
}

package com.hyperz.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.hyperz.Entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE categoryid = :categoryId")
    List<Product> getByCategory(int categoryId);

    @Query("SELECT * FROM Product WHERE id = :id LIMIT 1")
    Product getProductById(int id);
}

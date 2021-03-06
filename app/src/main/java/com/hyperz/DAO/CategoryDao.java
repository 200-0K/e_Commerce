package com.hyperz.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.hyperz.Entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM Category")
    List<Category> getAll();
}

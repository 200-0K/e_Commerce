package com.hyperz.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hyperz.DAO.CategoryDao;
import com.hyperz.DAO.ProductDao;
import com.hyperz.Entity.Category;
import com.hyperz.Entity.Product;

@Database(entities = {Product.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase appDatabase;
    private static final String DB_NAME = "HyperZDB.db";

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null)
            return appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).createFromAsset("database/" + DB_NAME).build();
        return appDatabase;
    }

    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
}

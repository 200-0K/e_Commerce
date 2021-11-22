package com.hyperz;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hyperz.Database.AppDatabase;
import com.hyperz.ViewModel.CategoryViewModel;
import com.hyperz.ViewModel.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.categoryFragment, R.id.accountFragment
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Load all products
        AsyncTask.execute(() -> {
            productViewModel.updateProducts(AppDatabase.getInstance(getApplicationContext()).productDao().getAll());
        });

        // Load all categories
        AsyncTask.execute(() -> {
            categoryViewModel.updateCategories(AppDatabase.getInstance(getApplicationContext()).categoryDao().getAll());
        });
    }
}
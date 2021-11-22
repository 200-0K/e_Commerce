package com.hyperz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyperz.Entity.Product;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final MutableLiveData<List<Product>> allProducts = new MutableLiveData<>();

    public void updateProducts(List<Product> products) {
        allProducts.postValue(products);
    }

    public LiveData<List<Product>> getProducts() {
        return allProducts;
    }
}

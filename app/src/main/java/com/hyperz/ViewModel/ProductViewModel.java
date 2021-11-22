package com.hyperz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyperz.entity.Product;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final MutableLiveData<List<Product>> allProducts = new MutableLiveData<>();
//    private final MutableLiveData<List<Product>> filteredProducts = new MutableLiveData<>();

    public void updateProducts(List<Product> products) {
        allProducts.postValue(products);
    }

    public LiveData<List<Product>> getFilteredProducts() {
        return allProducts;
    }
}

package com.hyperz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyperz.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private List<Product> products;
    private final MutableLiveData<List<Product>> liveProducts = new MutableLiveData<>();

    public void updateProducts(List<Product> products) {
        this.products = products;
        showCurrentProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return liveProducts;
    }

    public void showCurrentProducts() {
        liveProducts.postValue(products);
    }

    public void search(String str) {
        if (str.length() < 1) {
            liveProducts.postValue(products);
            return;
        }

        str = str.toLowerCase();
        List<Product> searchResultProducts = new ArrayList<>();
        for(Product product : products) {
            if (product.title.toLowerCase().contains(str)) searchResultProducts.add(product);
        }

        liveProducts.postValue(searchResultProducts);
    }
}

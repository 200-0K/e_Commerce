package com.hyperz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyperz.entity.Category;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private final MutableLiveData<List<Category>> allCategories = new MutableLiveData<>();

    public void updateCategories(List<Category> categories) {
        allCategories.postValue(categories);
    }

    public LiveData<List<Category>> getCategories() {return allCategories;}
}

package com.hyperz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hyperz.Component.CategoryTextView;
import com.hyperz.Database.AppDatabase;
import com.hyperz.Entity.Category;
import com.hyperz.ViewModel.CategoryViewModel;
import com.hyperz.ViewModel.ProductViewModel;

public class CategoryFragment extends Fragment {
    private CategoryViewModel categoryViewModel;
    private ProductViewModel productViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout container = getView().findViewById(R.id.category_container);

        categoryViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            container.removeAllViews();
            if (categories == null) return;
            for (Category category : categories) {
                CategoryTextView categoryTextView = new CategoryTextView(getContext(), category);
                categoryTextView.setOnClickListener(this::categoryClicked);
                container.addView(categoryTextView);
            }
        });
    }

    public void categoryClicked(View view) {
        Category category = ((CategoryTextView) view).getCategory();
        AsyncTask.execute(() -> {
            productViewModel.updateProducts(AppDatabase.getInstance(getActivity().getApplicationContext()).productDao().getByCategory(category.id));
        });
        Navigation.findNavController(view).navigate(R.id.homeFragment);
    }
}
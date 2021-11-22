package com.hyperz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hyperz.Component.ProductCardView;
import com.hyperz.Database.AppDatabase;
import com.hyperz.ViewModel.ProductViewModel;
import com.hyperz.entity.Product;

public class HomeFragment extends Fragment {
    private ProductViewModel productViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        GridLayout container = (GridLayout) getView().findViewById(R.id.productContainer);
        ImageButton uiCart = (ImageButton) getView().findViewById(R.id.cart);
        EditText uiSearch = (EditText) getView().findViewById(R.id.search);

        productViewModel.getFilteredProducts().observe(getViewLifecycleOwner(), products -> {
            container.removeAllViews();
            for(Product product : products) {
                CardView card = new ProductCardView(getContext(), product);
                card.setOnClickListener(this::productClicked);
                container.addView(card);
            }
        });

        uiCart.setOnClickListener(this::cartClicked);

        //TODO: Handle search
//        uiSearch.addTextChangedListener(new searchChanged());

        // Load all products
        AsyncTask.execute(() -> {
            productViewModel.updateProducts(AppDatabase.getInstance(getActivity().getApplicationContext()).productDao().getAll());
        });
    }

    public void cartClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.accountFragment); // for now...
    }

    public void productClicked(View view) {
        //TODO: Forward to product details activity with product id
        Log.d("Product", "onViewCreated: " + ((ProductCardView) view).getProduct().id );
    }

    class searchChanged implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}


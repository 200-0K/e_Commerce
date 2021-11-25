package com.hyperz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hyperz.Component.ProductCardView;
import com.hyperz.Entity.Product;
import com.hyperz.ViewModel.ProductViewModel;

public class HomeFragment extends Fragment {
    private ProductViewModel productViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridLayout container = (GridLayout) getView().findViewById(R.id.productContainer);
        ImageButton uiCart = (ImageButton) getView().findViewById(R.id.cart);
        EditText uiSearch = (EditText) getView().findViewById(R.id.search);

        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            container.removeAllViews();
            if (products == null) return;
            for(Product product : products) {
                CardView card = new ProductCardView(getContext(), product);
                card.setOnClickListener(this::productClicked);
                container.addView(card);
            }
        });

        uiCart.setOnClickListener(this::cartClicked);

        uiSearch.addTextChangedListener(new SearchChanged());

        productViewModel.showCurrentProducts();
    }

    public void cartClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.accountFragment); // for now...
    }

    public void productClicked(View view) {
        Product product = ((ProductCardView) view).getProduct();
        Intent intent = new Intent(getActivity(), ProductDetail.class);
        intent.putExtra(Intent.EXTRA_REFERRER, product.id);
        startActivity(intent);
    }

    class SearchChanged implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            AsyncTask.execute(() -> {
                productViewModel.search(s.toString());
            });
        }
    }
}


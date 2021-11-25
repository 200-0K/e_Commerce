package com.hyperz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.hyperz.Database.AppDatabase;
import com.hyperz.Entity.Product;
import com.hyperz.Helper.Text;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    private int productid;
    private final MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();

    private EditText uiQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        uiQty = findViewById(R.id.add_to_cart_qty);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        productid = getIntent().getIntExtra(Intent.EXTRA_REFERRER, -1);

        if(productid == -1) {
            finish();
            return;
        }

        productMutableLiveData.observe(this, this::updateDetails);

        AsyncTask.execute(() -> {
            productMutableLiveData.postValue(AppDatabase.getInstance(getApplicationContext()).productDao().getProductById(productid));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void updateDetails(Product product) {
        ImageView imageView = findViewById(R.id.product_image);
        Picasso.get().load(product.image).into(imageView);

        TextView rate = findViewById(R.id.rate);
        rate.setText(String.valueOf(product.rate));

        TextView title = findViewById(R.id.product_title);
        title.setText(product.title);

        TextView price = findViewById(R.id.product_price);
        price.setText(Text.formatPrice(product.price));

        TextView description = findViewById(R.id.product_description);
        description.setText(product.description);
    }

    public void minus(View view) {
        int qty;
        try {
            qty = Integer.parseInt(uiQty.getText().toString());
        } catch (NumberFormatException e) {return;}
        if (qty < 2) return;

        uiQty.setText(String.valueOf(qty-1));
    }

    public void plus(View view) {
        int qty;
        try {
            qty = Integer.parseInt(uiQty.getText().toString());
        } catch (NumberFormatException e) {return;}

        uiQty.setText(String.valueOf(qty+1));
    }

    public void addToCart(View view) {
        Toast.makeText(this, "You have to be logged in to perform this action", Toast.LENGTH_LONG).show();
    }
}
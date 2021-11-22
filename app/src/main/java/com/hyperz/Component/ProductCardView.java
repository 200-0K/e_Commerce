package com.hyperz.Component;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.hyperz.Entity.Product;
import com.squareup.picasso.Picasso;

public class ProductCardView extends CardView {
    private Product product;

    private LinearLayout verticalContainer;
    private ImageView uiImageView;
    private TextView uiTitle;
    private TextView uiPrice;

    private final int cardWidth = 400;
    private final int imageHeight = 300;
    private final int titleHeight = 30;

    public ProductCardView(@NonNull Context context, Product product) {
        super(context);
        this.product = product;

        GridLayout.LayoutParams cardParams = new GridLayout.LayoutParams();
        cardParams.setMargins(25, 25, 25, 25);
        this.setContentPadding(15, 15, 15, 15);
        this.setClickable(true);
        this.setForeground(context.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground}).getDrawable(0)); // ripple effect on click
        this.setLayoutParams(cardParams);

        verticalContainer = new LinearLayout(context);
        FrameLayout.LayoutParams verticalParams = new FrameLayout.LayoutParams(cardWidth, LayoutParams.WRAP_CONTENT);
        verticalContainer.setLayoutParams(verticalParams);
        verticalContainer.setOrientation(LinearLayout.VERTICAL);

        uiImageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, imageHeight);
        imageParams.setMargins(0, 0, 0, 5);
        uiImageView.setLayoutParams(imageParams);

        uiTitle = new TextView(context);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, titleHeight);
        titleParams.setMargins(0 , 10, 0, 10);
        uiTitle.setAutoSizeTextTypeUniformWithConfiguration(10, 20, 1, TypedValue.COMPLEX_UNIT_SP);
        uiTitle.setSingleLine(true);
        uiTitle.setEllipsize(TextUtils.TruncateAt.END);
        uiTitle.setLayoutParams(titleParams);

        uiPrice = new TextView(context);

        verticalContainer.addView(uiImageView);
        verticalContainer.addView(uiTitle);
        verticalContainer.addView(uiPrice);

        this.addView(verticalContainer);
        updateCard(product);
    }

    public void updateCard(Product p) {
        Picasso.get().load(p.image).into(uiImageView);
        uiTitle.setText(p.title);
        uiPrice.setText("SAR " + p.price);
    }

    public Product getProduct() {
        return product;
    }
}

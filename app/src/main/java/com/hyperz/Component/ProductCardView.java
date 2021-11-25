package com.hyperz.Component;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.hyperz.Entity.Product;
import com.hyperz.Helper.Text;
import com.hyperz.R;
import com.squareup.picasso.Picasso;

public class ProductCardView extends CardView {
    private Product product;

    private ImageView uiImageView;
    private TextView uiTitle;
    private TextView uiPrice;

    private final int nCardPerRow = 2;

    public ProductCardView(@NonNull Context context, Product product) {
        super(context);
        this.product = product;

        GridLayout.LayoutParams cardParams = new GridLayout.LayoutParams();
        cardParams.setMargins(25, 25, 25, 25);
        this.setLayoutParams(cardParams);
        this.setContentPadding(15, 15, 15, 15);
        this.setClickable(true);
        this.setForeground(context.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground}).getDrawable(0)); // ripple effect on click

        int hrPadding = this.getContentPaddingLeft() + this.getContentPaddingRight();
        int hrMargin = cardParams.leftMargin + cardParams.rightMargin;
        int cardWidth = (Resources.getSystem().getDisplayMetrics().widthPixels/nCardPerRow) - (hrMargin + hrPadding);

        LayoutInflater inflater = LayoutInflater.from(context);
        View cardContent = inflater.inflate(R.layout.product_card, null);
        FrameLayout.LayoutParams contentParams = new FrameLayout.LayoutParams(cardWidth, LayoutParams.WRAP_CONTENT);
        cardContent.setLayoutParams(contentParams);
        uiImageView = cardContent.findViewById(R.id.prodcut_card_image);
        uiTitle = cardContent.findViewById(R.id.product_card_title);
        uiPrice = cardContent.findViewById(R.id.product_card_price);

        addView(cardContent);
        updateCard(product);
    }

    public void updateCard(Product p) {
        Picasso.get().load(p.image).into(uiImageView);
        uiTitle.setText(p.title);
        uiPrice.setText( "SAR ".concat(Text.formatPrice(p.price)) );
    }

    public Product getProduct() {
        return product;
    }
}

package com.hyperz.Component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.hyperz.Entity.Category;

public class CategoryTextView extends AppCompatTextView {
    private Category category;
    private final float imageSize = 1f;
    private final int fontSize = 20;

    public CategoryTextView(Context context, Category category) {
        super(context);
        this.category = category;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 0, 0, 30);
        this.setPadding(10, 20, 10, 20);
        this.setLayoutParams(params);
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setClickable(true);
        this.setForeground(context.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground}).getDrawable(0)); // ripple effect on click

        this.setText(category.name.substring(0,1).toUpperCase().concat(category.name.substring(1))); // capitalize first letter
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);

        int size = (int)Math.round(this.getLineHeight() * imageSize);
        Drawable drawable = context.getResources().getDrawable(context.getResources().getIdentifier(category.icon, "drawable", context.getPackageName()));
        drawable.setBounds(0, 0, size, size);

        this.setCompoundDrawables(drawable, null, null, null);
        this.setCompoundDrawablePadding(size/2);
    }

    public Category getCategory() {return category;}
}

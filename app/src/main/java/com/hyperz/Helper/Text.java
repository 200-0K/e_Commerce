package com.hyperz.Helper;

import java.text.NumberFormat;
import java.util.Locale;

public class Text {

    public static String formatPrice(double price) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(price);
    }
}

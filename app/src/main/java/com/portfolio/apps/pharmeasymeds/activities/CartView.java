package com.portfolio.apps.pharmeasymeds.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.portfolio.apps.pharmeasymeds.R;

public class CartView extends AppCompatActivity {

    TextView cartValuePrice;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_screen);

        TextView amountToBePaid = (TextView)findViewById(R.id.amount_to_be_paid);
        TextView priceTotal = (TextView)findViewById(R.id.price_total);

        intent = getIntent();

        int price = intent.getIntExtra("price",0);
        int total_cart_value = price +99;

        amountToBePaid.setText(String.valueOf(total_cart_value));
        priceTotal.setText("₹ "+total_cart_value);
        cartValuePrice = (TextView)findViewById(R.id.cart_value_price);
        cartValuePrice.setText(String.valueOf(price));
    }

}

package com.portfolio.apps.pharmeasymeds.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.portfolio.apps.pharmeasymeds.R;

import org.w3c.dom.Text;

public class ProductDescription extends AppCompatActivity {
    TextView addToCart;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_description);

        intent = getIntent();
        final int price = getIntent().getIntExtra("price",0);
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String offerPrice = getIntent().getStringExtra("offer");
        String mrpPrice = getIntent().getStringExtra("mrpPrice");
        String nameOfTheProduct = getIntent().getStringExtra("name");

        ImageView imageView = (ImageView)findViewById(R.id.product_thumbnail);
        TextView mrp = (TextView)findViewById(R.id.mrp);
        TextView offer = (TextView)findViewById(R.id.offer);
        TextView saleTextView = (TextView)findViewById(R.id.sale_price);
        TextView nameOfTheProductTv = (TextView)findViewById(R.id.product_title);

        mrp.setText("₹ "+mrpPrice);
        offer.setText("₹ "+offerPrice);
        saleTextView.setText("₹ "+String.valueOf(price));
        nameOfTheProductTv.setText(nameOfTheProduct);

        Glide.with(getApplicationContext()).load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

        addToCart = (TextView)findViewById(R.id.add_to_cart_button);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDescription.this, CartView.class);
                i.putExtra("price",price);
                startActivity(i);
            }
        });
    }
}

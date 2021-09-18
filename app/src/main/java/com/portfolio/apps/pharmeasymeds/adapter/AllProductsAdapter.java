package com.portfolio.apps.pharmeasymeds.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.portfolio.apps.pharmeasymeds.Application;
import com.portfolio.apps.pharmeasymeds.Pojo.AllProducts;
import com.portfolio.apps.pharmeasymeds.Pojo.AllProducts;
import com.portfolio.apps.pharmeasymeds.R;
import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;

import java.util.ArrayList;
import java.util.List;

public class AllProductsAdapter extends ArrayAdapter<AllProducts> {
    ArrayList<AllProducts> allProducts;
    public AllProductsAdapter(@NonNull Context context, ArrayList<AllProducts> allProducts) {
        super(context, 0, allProducts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.all_products_list_row, parent, false);
        }

        AllProducts allProducts= getItem(position);

        TextView allProductsName = listitemView.findViewById(R.id.product_title);
        TextView mrpOfProduct = listitemView.findViewById(R.id.inr_of_product);
        TextView salePriceOfProduct = listitemView.findViewById(R.id.product_discounted_price);
        TextView offerPriceOnProduct = listitemView.findViewById(R.id.offer_percent);
        ImageView productPic = listitemView.findViewById(R.id.product_image);

        allProductsName.setText(allProducts.getTitleOfTheProduct());
        mrpOfProduct.setText("₹ "+allProducts.getMrpOfTheProduct());
        salePriceOfProduct.setText("₹ "+allProducts.getSalePriceOfTheProduct());
        offerPriceOnProduct.setText(allProducts.getOfferOnTheProduct());
        Context context = Application.getContext();

        Glide.with(context).load(allProducts.getImageUrlOftheProduct())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.warning_image)
                .into(productPic);

        return listitemView;
    }
    public void refresh(List<AllProducts> items)
    {
        this.allProducts = (ArrayList<AllProducts>) items;
        notifyDataSetChanged();
    }
}

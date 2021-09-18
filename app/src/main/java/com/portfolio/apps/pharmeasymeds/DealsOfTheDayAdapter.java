package com.portfolio.apps.pharmeasymeds;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DealsOfTheDayAdapter extends RecyclerView.Adapter<DealsOfTheDayAdapter.ViewHolder> {
    private ArrayList<DealsOfTheDay> localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView product_name;
        private final TextView product_mrp;
        private final TextView product_current_price;
        private final ImageView product_image;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            product_name = (TextView) view.findViewById(R.id.deals_of_the_day_title);
            product_mrp = (TextView) view.findViewById(R.id.inr);
            product_current_price = (TextView) view.findViewById(R.id.discounted_price);
            product_image = (ImageView) view.findViewById(R.id.deals_image);

        }

        public TextView getProduct_name() {
            return product_name;
        }

        public TextView getProduct_mrp() {
            return product_mrp;
        }

        public TextView getProduct_current_price() {
            return product_current_price;
        }

        public ImageView getProduct_image() {
            return product_image;
        }
      }
        public DealsOfTheDayAdapter(ArrayList<DealsOfTheDay> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.deals_of_the_day_list_row, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            Context context = Application.getContext();
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getProduct_name().setText(localDataSet.get(position).getNameOfTheProduct());
            viewHolder.getProduct_mrp().setText(localDataSet.get(position).getMrpOfTheProduct());
            viewHolder.getProduct_current_price().setText(localDataSet.get(position).getDiscountedPriceOfTheProduct());
            String imageUrl = localDataSet.get(position).getDrawableOfTheCategory();
            Glide.with(context).load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.product_image);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }

}

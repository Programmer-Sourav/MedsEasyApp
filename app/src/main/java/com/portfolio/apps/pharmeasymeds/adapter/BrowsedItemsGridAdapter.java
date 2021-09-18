package com.portfolio.apps.pharmeasymeds.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.portfolio.apps.pharmeasymeds.Pojo.BrowsedItems;

import com.portfolio.apps.pharmeasymeds.R;

import java.util.ArrayList;

public class BrowsedItemsGridAdapter extends ArrayAdapter<BrowsedItems>

    {
    public BrowsedItemsGridAdapter(@NonNull Context context, ArrayList<BrowsedItems> browsedModelArrayList) {
        super(context, 0, browsedModelArrayList);
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_layout_representation_for_browsed_items, parent, false);
        }
        BrowsedItems medicineCategoryModel = getItem(position);
        TextView medicineCategoryName = listitemView.findViewById(R.id.medicine_category_name);
        ImageView medicineCategoryPic = listitemView.findViewById(R.id.medicine_category_pic_id);
        TextView originalMrp = listitemView.findViewById(R.id.original_mrp);
        TextView sellingPrice = listitemView.findViewById(R.id.selling_price);
        medicineCategoryName.setText(medicineCategoryModel.getNameOfTheCategory());
        medicineCategoryPic.setImageResource(medicineCategoryModel.getDrawableOfTheCategory());
        originalMrp.setText(medicineCategoryModel.getMax_retail_price());
        originalMrp.setPaintFlags(originalMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        sellingPrice.setText(medicineCategoryModel.getSelling_price());

        return listitemView;
    }
}

package com.portfolio.apps.pharmeasymeds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.portfolio.apps.pharmeasymeds.Pojo.MedicineCategory;
import com.portfolio.apps.pharmeasymeds.R;

import java.util.ArrayList;

public class GridViewCustomAdapter extends ArrayAdapter<MedicineCategory> {
    public GridViewCustomAdapter(@NonNull Context context, ArrayList<MedicineCategory> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_view_each_item_representation, parent, false);
        }
        MedicineCategory medicineCategoryModel = getItem(position);
        TextView medicineCategoryName = listitemView.findViewById(R.id.medicine_category_name);
        ImageView medicineCategoryPic = listitemView.findViewById(R.id.medicine_category_pic_id);
        medicineCategoryName.setText(medicineCategoryModel.getNameOfTheCategory());
        medicineCategoryPic.setImageResource(medicineCategoryModel.getDrawableOfTheCategory());
        return listitemView;
    }
}

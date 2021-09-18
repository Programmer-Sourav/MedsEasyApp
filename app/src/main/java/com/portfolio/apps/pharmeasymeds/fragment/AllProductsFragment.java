package com.portfolio.apps.pharmeasymeds.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.portfolio.apps.pharmeasymeds.DealsOfTheDay;
import com.portfolio.apps.pharmeasymeds.MyViewModelFactory;
import com.portfolio.apps.pharmeasymeds.Pojo.AllProducts;
import com.portfolio.apps.pharmeasymeds.Pojo.MedicineCategory;
import com.portfolio.apps.pharmeasymeds.R;
import com.portfolio.apps.pharmeasymeds.ViewModel.AllProductsViewModel;
import com.portfolio.apps.pharmeasymeds.ViewModel.DealsOfTheDayViewModel;
import com.portfolio.apps.pharmeasymeds.activities.ProductDescription;
import com.portfolio.apps.pharmeasymeds.adapter.AllProductsAdapter;
import com.portfolio.apps.pharmeasymeds.adapter.GridViewCustomAdapter;
import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;

import java.util.ArrayList;
import java.util.List;

public class AllProductsFragment extends Fragment {

    View view;
    GridView productsGrid;
    AllProductsViewModel allProductsViewModel;
    ArrayList<AllProducts> allProducts = new ArrayList<>();
    AllProductsAdapter adapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.categoriwise_grid_view, container, false);

        final Dialog mBottomSheetDialog = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(R.layout.custom_bottom_sheet_view);
        productsGrid = (GridView) view.findViewById(R.id.all_products);
        TextView sortButton = (TextView)view.findViewById(R.id.sort);

        allProductsViewModel = ViewModelProviders.of(this, new MyAllProductsViewModelFactory(getActivity().getApplication(), "my awesome param")).get(AllProductsViewModel.class);
        adapter = new AllProductsAdapter(getActivity(), allProducts);
        productsGrid.setAdapter(adapter);

        productsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Intent productDesc = new Intent(getActivity(), ProductDescription.class);
                                                    int price = Integer.parseInt(allProducts.get(position).getSalePriceOfTheProduct());
                                                    String imageUrl = allProducts.get(position).getImageUrlOftheProduct();
                                                    String offerPercent = allProducts.get(position).getOfferOnTheProduct();
                                                    String mrpPrice = allProducts.get(position).getMrpOfTheProduct();
                                                    String nameOfProduct = allProducts.get(position).getTitleOfTheProduct();
                                                    productDesc.putExtra("price", price);
                                                    productDesc.putExtra("imageUrl", imageUrl);
                                                    productDesc.putExtra("offer", offerPercent);
                                                    productDesc.putExtra("mrpPrice", mrpPrice);
                                                    productDesc.putExtra("name", nameOfProduct);

                                                    startActivity(productDesc);

                                                }
    });


        getAllProducts();

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // your custom view.
                mBottomSheetDialog.setCancelable(true);
                mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mBottomSheetDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
                mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
                mBottomSheetDialog.show();
            }
        });

        TextView selectButton = (TextView)mBottomSheetDialog.findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void getAllProducts(){
        allProductsViewModel.getAllProductsResponseLiveData().observe(getViewLifecycleOwner(), new Observer<AllProductsResponse>() {
            @Override
            public void onChanged(AllProductsResponse allProductsResponse) {
                if (allProductsResponse != null) {
                    List<AllProducts> allProduct = allProductsResponse.getAllProductsList();
                    allProducts.addAll(allProduct);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
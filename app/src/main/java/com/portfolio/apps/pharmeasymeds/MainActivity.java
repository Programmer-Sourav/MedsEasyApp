package com.portfolio.apps.pharmeasymeds;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.portfolio.apps.pharmeasymeds.Pojo.BrowsedItems;
import com.portfolio.apps.pharmeasymeds.Pojo.MedicineCategory;

import com.portfolio.apps.pharmeasymeds.Pojo.SliderItem;
import com.portfolio.apps.pharmeasymeds.ViewModel.DealsOfTheDayViewModel;
import com.portfolio.apps.pharmeasymeds.adapter.BrowsedItemsGridAdapter;
import com.portfolio.apps.pharmeasymeds.adapter.GridViewCustomAdapter;
import com.portfolio.apps.pharmeasymeds.adapter.SliderAdapter;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;
import com.portfolio.apps.pharmeasymeds.uploadservice.UploadWorker;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 101 ;
    GridView medicine_category_grid;
    ArrayList<MedicineCategory> medicineCategoryList = new ArrayList<>();

    GridView previously_browsed_items;
    ArrayList<BrowsedItems> previouslyBrowsedItems = new ArrayList<>();

    ArrayList<DealsOfTheDay> dealsOfTheDay = new ArrayList<>();
    RecyclerView dealsOfTheList;
    SliderAdapter sliderAdapter;

    DealsOfTheDayViewModel dealsOfTheDayViewModel;
    DealsOfTheDayAdapter adapter;

    TextView uploadPrescription;
    private int PICK_PDF_REQUEST5 = 5;
    private int REQUEST_CODE5 = 1005;

    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        medicine_category_grid = (GridView)findViewById(R.id.medicine_category_grid);
        previously_browsed_items = (GridView)findViewById(R.id.medicine_browsed);
        dealsOfTheList = (RecyclerView)findViewById(R.id.dealsOfTheDayList);

        dealsOfTheDayViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), "my awesome param")).get(DealsOfTheDayViewModel.class);
        /*** This is a grid view with static items *****/
        medicineCategoryList.add(new MedicineCategory(R.mipmap.covid_essentials,"Covid Essentials"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.devices,"Devices"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.nutrition_fitness,"Nutrition-Fitness"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.personal_care,"Personal Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.ayurvedic,"Ayurveda Wellness"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.baby_care,"Baby Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.skin_care_1,"Skin Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.diabetic_care,"Diabetic Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.sexual_well,"Sexual Wellness"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.short_term,"Acute Problems"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.daily_cure,"Lifestyle Ailments"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.home_care,"Home Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.women_care,"Women Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.health_food,"Health Products"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.ortho_care,"Ortho Care"));
        medicineCategoryList.add(new MedicineCategory(R.mipmap.pain_cure,"Pain Relief"));
        /**** List with dummy items ****/
        previouslyBrowsedItems.add(new BrowsedItems(R.drawable.browsed_item_iodex,"Iodex Ultra gel 60ml small pack","₹599.00","₹425"));
        previouslyBrowsedItems.add(new BrowsedItems(R.mipmap.devices,"Oxymeter from Best Company for Covid daily use","₹1280.00","₹1150"));
        previouslyBrowsedItems.add(new BrowsedItems(R.drawable.browsed_item_iodex,"Iodex Ultra gel 60ml small pack","₹599.00","₹425"));
        previouslyBrowsedItems.add(new BrowsedItems(R.mipmap.handwash,"Super Handwash for extra gentle care","₹98.00","₹85"));

        uploadPrescription = findViewById(R.id.upload_prescription);
//        dealsOfTheDay.add(new DealsOfTheDay(R.mipmap.women_care,"Hair Serum", "101.00", "95.00"));
//        dealsOfTheDay.add(new DealsOfTheDay(R.mipmap.health_food,"Nutrilite", "220.00", "189.00"));
//        dealsOfTheDay.add(new DealsOfTheDay(R.mipmap.rumalaya_forte,"Rumalaya Forte", "158.00", "148.00"));
//        dealsOfTheDay.add(new DealsOfTheDay(R.mipmap.pain_cure,"Volini gel", "65.00","57.50"));
        // View Model

        //dealsOfTheDayViewModel = ViewModelProviders.of(this).get(DealsOfTheDayViewModel.class);

        uploadPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileManager();
            }
        });

        requestStoragePermission();
        GridViewCustomAdapter medicineCategoryGridAdapter = new GridViewCustomAdapter(this,medicineCategoryList);
        medicine_category_grid.setAdapter(medicineCategoryGridAdapter);

        medicine_category_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openIntent;
                switch (position) {
                    case 0:

                    case 2:

                    case 3:

                    case 4:

                    case 5:

                    case 6:

                    case 7:

                    case 8:

                    case 9:

                    case 10:

                    case 11:

                    case 12:

                    case 13:

                    case 14:

                    case 15:

                    default:
                        openIntent = new Intent(MainActivity.this,ProductsByCategory.class);
                        startActivity(openIntent);
                        break;

                }
            }
        });
        BrowsedItemsGridAdapter browsedItemsGridAdapter = new BrowsedItemsGridAdapter(this,previouslyBrowsedItems);
        previously_browsed_items.setAdapter(browsedItemsGridAdapter);

        adapter = new DealsOfTheDayAdapter(dealsOfTheDay);
        // Attach the adapter to the recyclerview to populate items
        // Set layout manager to position the items
        dealsOfTheList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dealsOfTheList.setAdapter(adapter);

        /*** deals of the day are populated from back-end API***/
        getDealsOfTheDay();

        SliderView sliderView = findViewById(R.id.imageSlider);

        sliderAdapter = new SliderAdapter(this);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        renewItems(sliderView);
        addNewItem(sliderView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

    }
    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);

                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");

                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");

            sliderItemList.add(sliderItem);
        }
        sliderAdapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (sliderAdapter.getCount() - 1 >= 0)
            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        sliderAdapter.addItem(sliderItem);
    }

    private void getDealsOfTheDay(){
        dealsOfTheDayViewModel.getDealsOfTheDayResponseLiveData().observe(this, new Observer<DealsOfTheDayResponse>() {
            @Override
            public void onChanged(DealsOfTheDayResponse dealsOfTheDayResponse) {
                if (dealsOfTheDayResponse != null) {
                    List<DealsOfTheDay> dealsOfTheDays = dealsOfTheDayResponse.getDealsOfTheDayList();
                    dealsOfTheDay.addAll(dealsOfTheDays);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void openFileManager() {
        Intent intent = new Intent();

        intent.setType("*/*");
        if (Build.VERSION.SDK_INT > 26){
            intent.setAction(Intent.ACTION_PICK);
        }
        else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_REQUEST5);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            String path = FilePath.getPath(MainActivity.this, filePath);
            /*** Starting worker to upload a prescription file****/
            OneTimeWorkRequest myDownloadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).setInputData(
                    new Data.Builder()
                            .putString("KEY_INPUT_URL", "https://www.souravnath.com/upload_file.php")
                            .putString("FILE_PATH", path)
                            //.putString("KEY_OUTPUT_FILE_NAME","videoplayback.mp4")
                            .build()
            ).build();
            WorkManager.getInstance(Application.getContext())
                    .enqueue(myDownloadWorkRequest);
        }
        else if(requestCode == REQUEST_CODE5 && resultCode == RESULT_OK )
        {

         }
    }
    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to PostAProject.this block
            //Here you can explain why you need PostAProject.this permission
            //Explain here why you need PostAProject.this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


}
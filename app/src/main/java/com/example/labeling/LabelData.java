package com.example.labeling;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.asura.library.posters.DrawableImage;
import com.asura.library.posters.Poster;
import com.asura.library.posters.RemoteImage;
import com.asura.library.posters.RemoteVideo;
import com.asura.library.views.PosterSlider;
import com.example.labeling.reqAndRes.HttpHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LabelData extends AppCompatActivity {
    //    List<PackOfData> profile = new ArrayList<>();
//    private ViewPager viewPager;
//    private DataListResponse res;
//    private CircleIndicator circleIndicator;
//    private MyPager myPager;
    public DBHelper mydb;
    private ArrayList<CheckBox> chItems;
    private String[] label;
    private int noPackInShow;
    List<Poster> posters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_data);
        ImageButton leftBtn = findViewById(R.id.start_of_data);
        leftBtn.bringToFront();
        posters = new ArrayList<>();
        chItems = new ArrayList<>();
        mydb = new DBHelper(this);

        mydb.insertData("image", "", 1);

        BottomNavigationView bottomNavigation = findViewById(R.id.nv);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean  onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_left :
                        if(noPackInShow > 0) {
                            posters.clear();
                            noPackInShow--;
                        }
                        return true;
                    case R.id.navigation_right :
                        if(noPackInShow < loadData().size()) {
                            noPackInShow++;
                            posters.clear();
                        }
                        return true;
                }
            return false;
            }
        });

        LinearLayout myLayout = findViewById(R.id.root_of_labels);
        ScrollView sv = new ScrollView(this);
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

//        TextView tv = new TextView(this);
//        tv.setText("Dynamic layouts ftw!");
//        ll.addView(tv);

        Button saveLabelSelectedBtn = new Button(this);
        saveLabelSelectedBtn.setText("stick labels to this pack");
        saveLabelSelectedBtn.setBackgroundColor(getResources().getColor(R.color.backGroundBtn1));
        ll.addView(saveLabelSelectedBtn);
        Button loadLabelBtn = new Button(this);
        loadLabelBtn.setText("load labels from server");
        loadLabelBtn.setBackgroundColor(getResources().getColor(R.color.backGroundBtn2));
        ll.addView(loadLabelBtn);

        loadLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < loadLabels().size(); i++) {
                    CheckBox chb = new CheckBox(getApplicationContext());
                    chb.setText(loadLabels().get(i).getLabels());
                    ll.addView(chb);
                    chItems.add(chb);
                }
            }
        });

        //click this button after that select your checkbox to save labels in
        // database.
        saveLabelSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int j = 0;
                for (int i = 0; i < chItems.size(); i++) {
                    if (chItems.get(i).isChecked()) {
//                        mydb.insertData(chItems.get(i).getText().toString());
                        label[j] = chItems.get(i).getText().toString();
                        j++;
                    }
                }
                hitLabel(loadData().get(noPackInShow),label);
            }
        });
        myLayout.addView(sv);
//        this.setContentView(sv);
        PosterSlider posterSlider = (PosterSlider) findViewById(R.id.poster_slider);



//        for (int i = 0; i < loadData().get(noPackInShow).getImageUri().length; i++){
//            posters.add(new RemoteImage(loadData().get(noPackInShow).getImageUri()[i]));
//        }



        posters.add(new DrawableImage(R.drawable.e1));
//        posters.add(new DrawableImage(R.drawable.e2));
//        posters.add(new DrawableImage(R.drawable.e3));
//        posters.add(new DrawableImage(R.drawable.e4));
//        Uri uri = Uri.parse("https://mov.tebyan.net/1395/11/222885_222885.mp4");
//        posters.add(new RemoteVideo(uri));
//        posters.add(new RawVideo(R.));
        posterSlider.setPosters(posters);

        //butter knife
//        ImageButton rightBtn = findViewById(R.id.end_of_data);
//        rightBtn.bringToFront();
//        String[] txt = {"g", "t", "r"};
//        PackOfData myP = new PackOfData();
//        myP.setText(txt);
//        myP.setImage(txt);
//        profile.add(myP);
//        res.setDataQ(profile);
//
//        for(PackOfData pod : res.getDataQ()){
//
//        }
//        myPager = new MyPager(this, profile);
//        viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(myPager);
//        circleIndicator = findViewById(R.id.circle);
//        circleIndicator.setViewPager(viewPager);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        // Loader image - will be shown before loading image
//        int loader = R.drawable.loadingicon;
//
//        // Imageview to show
//        ImageView image = (ImageView) findViewById(R.id.img);
//
//        // Image url
//        String image_url = "https://imgurl.ir/uploads/j24962_.jpg";
//
//        // ImageLoader class instance
//        final ImageLoader imgLoader = new ImageLoader(getApplicationContext());
//
//        // whenever you want to load an image from url
//        // call DisplayImage function
//        // url - image url to load
//        // loader - loader image, will be displayed before getting image
//        // image - ImageView
//        imgLoader.DisplayImage(image_url, loader, image);

//        final TextView txt1 = findViewById(R.id.txt_in_Scroll);
//        final NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollView1);
//        scrollView.setOnTouchListener(new OnSwipeTouchListener(LabelData.this) {
//            public void onSwipeLeft() {
////                txt.setText("hello!");
//            }
//        });
//        RelativeLayout rl = findViewById(R.id.RelativeL);
////        final ImageView img = findViewById(R.id.img);
//        rl.setOnTouchListener(new OnSwipeTouchListener(LabelData.this) {
//            public void onSwipeLeft() {
////                img.setImageResource(R.drawable.rightchevron);
//            }
//        });

    }

    private ArrayList<PackOfData> loadData() {
        final ArrayList<PackOfData> items = new ArrayList<>();

        PaginationRequest requestJson = new PaginationRequest();
        // requestJson should be value...
        RequestBody body = RequestBody.create(HttpHelper.JSON, new Gson().toJson(requestJson));
        Request request = new Request.Builder()
                .addHeader("Authorization", HttpHelper.getInstance().getLoginHeader(LabelData.this))
                .post(body)
                .url(HttpAddresses.AllImages).build();
        HttpHelper.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LabelData.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LabelData.this, "Unknown Problem", Toast.LENGTH_SHORT).show();
//                        callback.done(0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Log.e("response", response.code() + responseString);
                DataListResponse res = new Gson().fromJson(responseString, DataListResponse.class);
                if (response.isSuccessful() && res.isSuccess()) {
                    if (res.getDataQ() != null) {
                        int noGroup = 0;
                        for (PackOfData dt : res.getDataQ()) {
                            items.add(dt);
                            for (int i = 0; i < dt.getImageUri().length; i++) {
                                mydb.insertData("image", dt.getImageUri()[i], noGroup);
                            }
                            for (int i = 0; i < dt.getVideoUri().length; i++) {
                                mydb.insertData("video", dt.getVideoUri()[i], noGroup);
                            }
                            for (int i = 0; i < dt.getText().length; i++) {
                                mydb.insertData("text", dt.getText()[i], noGroup);
                            }
                            noGroup++;
                        }
//                        callback.done(res.getDataQ().size());
                    } else {
//                        callback.done(0);
                    }
                } else if (response.code() == 401 || res.getCode() == 401) {
//                    LabelData.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(LabelData.this, "Login Required", Toast.LENGTH_LONG).show();
//                            Intent loginActivityIntent = new Intent(getActivity(), LoginActivity.class);
//                            getActivity().finish();
//                            getActivity().startActivity(loginActivityIntent);
//                        }
//                    });
//                    callback.done(0);
                } else {
                    LabelData.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LabelData.this, "Unknown Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    callback.done(0);
                }
            }
        });
        return items;
    }

    private void hitLabel(PackOfData pod, String[] labelSelected) {
        pod.setLabels(labelSelected);
    }

    private ArrayList<AllLabels> loadLabels() {
        final ArrayList<AllLabels> items = new ArrayList<>();

        PaginationRequest requestJson = new PaginationRequest();
        // requestJson should be value...
        RequestBody body = RequestBody.create(HttpHelper.JSON, new Gson().toJson(requestJson));
        Request request = new Request.Builder()
                .addHeader("Authorization", HttpHelper.getInstance().getLoginHeader(LabelData.this))
                .post(body)
                .url(HttpAddresses.AllImages).build();
        HttpHelper.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LabelData.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LabelData.this, "Unknown Problem", Toast.LENGTH_SHORT).show();
//                        callback.done(0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Log.e("response", response.code() + responseString);
                LabelListResponse res = new Gson().fromJson(responseString, LabelListResponse.class);
                if (response.isSuccessful() && res.isSuccess()) {
                    if (res.getLabelQ() != null) {
                        for (AllLabels labels : res.getLabelQ()) {
                            items.add(labels);
//                            mydb.insertData(labels.getLabels());
                        }
//                        callback.done(res.getDataQ().size());
                    } else {
//                        callback.done(0);
                    }
                } else if (response.code() == 401 || res.getCode() == 401) {
//                    LabelData.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(LabelData.this, "Login Required", Toast.LENGTH_LONG).show();
//                            Intent loginActivityIntent = new Intent(getActivity(), LoginActivity.class);
//                            getActivity().finish();
//                            getActivity().startActivity(loginActivityIntent);
//                        }
//                    });
//                    callback.done(0);
                } else {
                    LabelData.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LabelData.this, "Unknown Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    callback.done(0);
                }
            }
        });
        return items;
    }

}

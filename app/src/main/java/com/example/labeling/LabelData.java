package com.example.labeling;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labeling.DataStorage.CommonUserLabelSelected;
import com.example.labeling.DataStorage.ImageUri_;
import com.example.labeling.DataStorage.LabelsList;
import com.example.labeling.DataStorage.Labels_;
import com.example.labeling.DataStorage.MasterUserLabelSelected;
import com.example.labeling.DataStorage.NoLabelList;
import com.example.labeling.DataStorage.NoPack;
import com.example.labeling.DataStorage.PackOfData;
import com.example.labeling.DataStorage.Text_;
import com.example.labeling.DataStorage.VideoUri_;
import com.example.labeling.reqAndRes.HttpHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LabelData extends AppCompatActivity {

//    @BindView(R.id.viewPager)
    ViewPager viewPager;
//    @BindView(R.id.Tag_fab)
    FloatingActionButton tagFab;
//    @BindView(R.id.Load_fab)
    FloatingActionButton loadFab, percentFab;
//    @BindView(R.id.start_of_data)
//    ImageButton leftBtn;
//    @BindView(R.id.end_of_data)
//    ImageButton rightBtn;
//    @BindView(R.id.nv)
    BottomNavigationView bottomNavigation;

//    private DataListResponse res;
//    private CircleIndicator circleIndicator;

    FragmentsPagerAdapter mAdapter;

    private ArrayList<CheckBox> chItems;
    private ArrayList<TextView> tvItems = new ArrayList<>();
    private ArrayList<String> checkedLabels = new ArrayList<>();
    private int noPackInShow;
    public PackOfData packInShow;
    private Realm dataRealm;
    private Realm labelRealm;
    public int flagForChangePack = 0;
    public static ArrayList<String> imageUrlFromDB = new ArrayList<String>();
    public static ArrayList<String> textFromDB = new ArrayList<String>();
    public static ArrayList<String> videoUrlFromDB = new ArrayList<String>();
    private String[] imageUrlF;
    List<Fragment> fragments;
    private LabelsList labelsInShow;
    private int noLabelListInShow;
    int tagClickCnt = 0;
    int loadClickCnt = 0;
    ArrayList<Integer> noEachLabelSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_data);
        ImageButton leftBtn = findViewById(R.id.start_of_data);
        leftBtn.bringToFront();
        ImageButton rightBtn = findViewById(R.id.end_of_data);
        rightBtn.bringToFront();
        viewPager = findViewById(R.id.viewPager);
        imageUrlFromDB.add("https://imgurl.ir/uploads/a833735_.png");
        imageUrlFromDB.add("https://imgurl.ir/uploads/b45910_.jpg");
        fragments = new ArrayList<>();
        mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        fragments = mAdapter.getFragments();
        mAdapter.setFragments(fragments);
        viewPager.setAdapter(mAdapter);
        chItems = new ArrayList<>();
        noPackInShow = 1;
        Realm.init(this);
        RealmConfiguration myconfig = new RealmConfiguration.Builder()
                .name("DRealm10.realm")
                .schemaVersion(3)
                .build();
        RealmConfiguration myconfig1 = new RealmConfiguration.Builder()
                .name("LRealm10.realm")
                .schemaVersion(3)
                .build();
        dataRealm = Realm.getInstance(myconfig);
        labelRealm = Realm.getInstance(myconfig1);

        /*
        first PackOfData that will be save in database.
         */
        RealmList<Text_> t1 = new RealmList<>();
        RealmList<ImageUri_> i1 = new RealmList<>();
        RealmList<VideoUri_> v1 = new RealmList<>();

        PackOfData packExist1 = dataRealm.where(PackOfData.class).equalTo("noPack_", 1).findFirst();
        if (packExist1 == null) {
            dataRealm.beginTransaction();
            Text_ mt1 = new Text_();
            mt1.setText("my first text in pack to show");
            final Text_ managedText1 = dataRealm.copyToRealm(mt1); // Persist unmanaged objects
            t1.add(mt1);

            ImageUri_ mi1 = new ImageUri_();
            mi1.setImageUri("https://www.imgurl.ir/uploads/a801954_.jpg");
            final ImageUri_ managedImageUri1 = dataRealm.copyToRealm(mi1); // Persist unmanaged objects
            i1.add(mi1);

            ImageUri_ mi2 = new ImageUri_();
            mi2.setImageUri("https://www.imgurl.ir/uploads/n061181_.jpg");
            final ImageUri_ managedImageUri2 = dataRealm.copyToRealm(mi2); // Persist unmanaged objects
            i1.add(mi2);

            ImageUri_ mi3 = new ImageUri_();
            mi3.setImageUri("https://www.imgurl.ir/uploads/x5262_.jpg");
            final ImageUri_ managedImageUri3 = dataRealm.copyToRealm(mi3); // Persist unmanaged objects
            i1.add(mi3);

            VideoUri_ mv1 = new VideoUri_();
            mv1.setVideoUri("https://www.imgurl.ir/uploads/t666542_.mp4");
            final VideoUri_ managedVideoUri1 = dataRealm.copyToRealm(mv1); // Persist unmanaged objects
            v1.add(mv1);

//        Labels_ ml1 = new Labels_();
//        ml1.setLabels("red");
//        final Labels_ managedLabels1 = dataRealm.copyToRealm(ml1);
//        l1.add(ml1);

            NoPack n1 = new NoPack();
            n1.setNoPack(1);
//            final NoPack managedNoPack1 = dataRealm.copyToRealm(n1);

//        Labels_ ml2 = new Labels_();
//        ml1.setLabels("green");
//        final Labels_ managedLabels2 = dataRealm.copyToRealm(ml2);


//        PackOfData p1 = new PackOfData();
//        p1.setNoPack(1);
//        p1.setLabels(l1);
//        p1.setText(t1);
//        p1.setImageUri(i1);
//        p1.setVideoUri(v1);

            PackOfData packOfData1 = dataRealm.createObject(PackOfData.class, 1); // Create managed objects directly
            packOfData1.getText().add(managedText1);
//            packOfData1.setNoPack(managedNoPack1);
            packOfData1.getImageUri().add(managedImageUri1);
            packOfData1.getImageUri().add(managedImageUri2);
            packOfData1.getImageUri().add(managedImageUri3);
            packOfData1.getVideoUri().add(managedVideoUri1);
            dataRealm.commitTransaction();
        }
//        pODDAO.save(p1, this);
        /*
        second PackOfData that will be save in database
         */
        PackOfData packExist2 = dataRealm.where(PackOfData.class).equalTo("noPack_", 2).findFirst();
        if (packExist2 == null) {
            dataRealm.beginTransaction();
            RealmList<Text_> t2 = new RealmList<>();
            RealmList<ImageUri_> i2 = new RealmList<>();
            RealmList<VideoUri_> v2 = new RealmList<>();
            RealmList<Labels_> l2 = new RealmList<>();

            Text_ mt2 = new Text_();
            mt2.setText("my second text in pack to show");
            final Text_ managedText2 = dataRealm.copyToRealm(mt2); // Persist unmanaged objects
            t2.add(mt2);

            ImageUri_ mi4 = new ImageUri_();
            mi4.setImageUri("https://imgurl.ir/uploads/m961881_.jpg");
            final ImageUri_ managedImageUri4 = dataRealm.copyToRealm(mi4); // Persist unmanaged objects
            i2.add(mi4);

            ImageUri_ mi5 = new ImageUri_();
            mi5.setImageUri("https://imgurl.ir/uploads/b687218_.jpg");
            final ImageUri_ managedImageUri5 = dataRealm.copyToRealm(mi5); // Persist unmanaged objects
            i2.add(mi5);

            ImageUri_ mi6 = new ImageUri_();
            mi6.setImageUri("https://imgurl.ir/uploads/b522675_.jpg");
            final ImageUri_ managedImageUri6 = dataRealm.copyToRealm(mi6); // Persist unmanaged objects
            i2.add(mi6);

            VideoUri_ mv2 = new VideoUri_();
            mv2.setVideoUri("https://imgurl.ir/uploads/y304_.mp4");
            final VideoUri_ managedVideoUri2 = dataRealm.copyToRealm(mv2); // Persist unmanaged objects
            v2.add(mv2);

            NoPack n2 = new NoPack();
            n2.setNoPack(2);
//            final NoPack managedNoPack2 = dataRealm.copyToRealm(n2);

//        PackOfData p2 = new PackOfData();
//        p2.setNoPack(2);
//        p2.setLabels(l2);
//        p2.setText(t2);
//        p2.setImageUri(i2);
//        p2.setVideoUri(v2);
//        pODDAO.save(p2, this);

            PackOfData packOfData2 = dataRealm.createObject(PackOfData.class, 2); // Create managed objects directly
            packOfData2.getText().add(managedText2);
//            packOfData2.setNoPack(managedNoPack2);
            packOfData2.getImageUri().add(managedImageUri4);
            packOfData2.getImageUri().add(managedImageUri5);
            packOfData2.getImageUri().add(managedImageUri6);
            packOfData2.getVideoUri().add(managedVideoUri2);
//        packOfData2.getLabels().add(managedLabels2);
            dataRealm.commitTransaction();
        }
        LabelsList listExist = labelRealm.where(LabelsList.class).equalTo("noList_", 1).findFirst();
        if (listExist == null) {
            labelRealm.beginTransaction();

            Labels_ lb1 = new Labels_();
            lb1.setLabels("red");
            final Labels_ managedLabel1 = labelRealm.copyToRealm(lb1);
//        labelRealm.commitTransaction();

//        labelRealm.beginTransaction();
            Labels_ lb2 = new Labels_();
            lb2.setLabels("green");
            final Labels_ managedLabel2 = labelRealm.copyToRealm(lb2);
//        labelRealm.commitTransaction();

//        labelRealm.beginTransaction();
            Labels_ lb3 = new Labels_();
            lb3.setLabels("yellow");
            final Labels_ managedLabel3 = labelRealm.copyToRealm(lb3);
//        labelRealm.commitTransaction();

//        labelRealm.beginTransaction();
            Labels_ lb4 = new Labels_();
            lb4.setLabels("pink");
            final Labels_ managedLabel4 = labelRealm.copyToRealm(lb4);
//        labelRealm.commitTransaction();

//        labelRealm.beginTransaction();
            Labels_ lb5 = new Labels_();
            lb5.setLabels("brown");
            final Labels_ managedLabel5 = labelRealm.copyToRealm(lb5);
//        labelRealm.commitTransaction();
            Labels_ lb6 = new Labels_();
            lb6.setLabels("grey");
            final Labels_ managedLabel6 = labelRealm.copyToRealm(lb6);

            Labels_ lb7 = new Labels_();
            lb7.setLabels("grey");
            final Labels_ managedLabel7 = labelRealm.copyToRealm(lb7);

//        labelRealm.beginTransaction();
            NoLabelList noLabelList = new NoLabelList();
            noLabelList.setNoList(1);
            final NoLabelList managednoList1 = labelRealm.copyToRealm(noLabelList);

            LabelsList labelslist1 = labelRealm.createObject(LabelsList.class);
            labelslist1.getLabels().add(managedLabel1);
            labelslist1.getLabels().add(managedLabel2);
            labelslist1.getLabels().add(managedLabel3);
            labelslist1.getLabels().add(managedLabel4);
            labelslist1.getLabels().add(managedLabel5);
            labelslist1.getLabels().add(managedLabel6);
            labelslist1.getLabels().add(managedLabel7);
            labelslist1.setNoLabelList(managednoList1);
            labelRealm.commitTransaction();
        }
//        Toast.makeText(this, labelRealm.getPath(), Toast.LENGTH_LONG).show();


//        packInShow = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();

        bottomNavigation = findViewById(R.id.nv);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dLeft:
                        if (noPackInShow > 1) {
//                            flagForChangePack = 2;
                            noPackInShow = 1;
                            Toast.makeText(getApplicationContext(), noPackInShow + "", Toast.LENGTH_SHORT).show();
                            packInShow = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(L)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++) {
                                textFromDB.add(packInShow.text.get(i).getText());
                                Log.i("get text from DB(L)", "Done");
                            }
                            videoUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getVideoUri().size(); i++) {
                                videoUrlFromDB.add(packInShow.videoUri.get(i).getVideoUri());
                                Log.i("get video from DB(L)", "Done");
                            }

                            mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
//                            fragmentTransaction = LabelData.this.getSupportFragmentManager().beginTransaction();
                            fragments = mAdapter.getFragments();
                            mAdapter.setFragments(fragments);
                            viewPager.setAdapter(mAdapter);
                        }
                        return true;
                    case R.id.navigation_left:
                        if (noPackInShow > 1) {
//                            flagForChangePack = 2;
                            noPackInShow--;
                            Toast.makeText(getApplicationContext(), noPackInShow + "", Toast.LENGTH_SHORT).show();
                            packInShow = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(L)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++) {
                                textFromDB.add(packInShow.text.get(i).getText());
                                Log.i("get text from DB(L)", "Done");
                            }
                            videoUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getVideoUri().size(); i++) {
                                videoUrlFromDB.add(packInShow.videoUri.get(i).getVideoUri());
                                Log.i("get video from DB(L)", "Done");
                            }

                            mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
//                            fragmentTransaction = LabelData.this.getSupportFragmentManager().beginTransaction();
                            fragments = mAdapter.getFragments();
                            mAdapter.setFragments(fragments);
                            viewPager.setAdapter(mAdapter);
                        }
                        return true;
                    case R.id.navigation_dRight:
                        if (noPackInShow < dataRealm.where(PackOfData.class).max("noPack_").intValue()) {
//                            Toast.makeText(getApplicationContext(),noPackInShow +"", Toast.LENGTH_SHORT).show();
//                            flagForChangePack = 3;
                            noPackInShow = dataRealm.where(PackOfData.class).max("noPack_").intValue();
                            Toast.makeText(getApplicationContext(), noPackInShow + "", Toast.LENGTH_SHORT).show();
                            packInShow = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(R)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++) {
                                textFromDB.add(packInShow.text.get(i).getText());
                                Log.i("get text from DB(R)", "Done");
                            }
                            videoUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getVideoUri().size(); i++) {
                                videoUrlFromDB.add(packInShow.videoUri.get(i).getVideoUri());
                                Log.i("get video from DB(R)", "Done");
                            }
                            mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
                            fragments = mAdapter.getFragments();
                            mAdapter.setFragments(fragments);
                            viewPager.setAdapter(mAdapter);
                        }
                        return true;
                    case R.id.navigation_right:
                        if (noPackInShow < dataRealm.where(PackOfData.class).max("noPack_").intValue()) {
//                            Toast.makeText(getApplicationContext(),noPackInShow +"", Toast.LENGTH_SHORT).show();
//                            flagForChangePack = 3;
                            noPackInShow++;
                            Toast.makeText(getApplicationContext(), dataRealm.where(PackOfData.class).max("noPack_").intValue() + "", Toast.LENGTH_SHORT).show();
                            packInShow = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(R)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++) {
                                textFromDB.add(packInShow.text.get(i).getText());
                                Log.i("get text from DB(R)", "Done");
                            }
                            videoUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getVideoUri().size(); i++) {
                                videoUrlFromDB.add(packInShow.videoUri.get(i).getVideoUri());
                                Log.i("get video from DB(R)", "Done");
                            }
                            mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
                            fragments = mAdapter.getFragments();
                            mAdapter.setFragments(fragments);
                            viewPager.setAdapter(mAdapter);
                        }
                        return true;
                }
                return false;
            }
        });

//        LinearLayout myLayout = findViewById(R.id.root_of_labels);
//        ScrollView sv = new ScrollView(this);
        LinearLayout ll = findViewById(R.id.llinSV);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        sv.addView(ll);
//        myLayout.addView(sv);

//        TextView tv = new TextView(this);
//        tv.setText("Dynamic layouts ftw!");
//        ll.addView(tv);

//        Button saveSelectedLabelsBtn = new Button(this);
//        saveSelectedLabelsBtn.setText("stick labels to this pack");
//        saveSelectedLabelsBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        saveSelectedLabelsBtn.setBackgroundColor(getResources().getColor(R.color.backGroundBtn1));
//        ll.addView(saveSelectedLabelsBtn);
//        Button loadLabelBtn = new Button(this);
//        loadLabelBtn.setText("load labels from server");
//        loadLabelBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        loadLabelBtn.setBackgroundColor(getResources().getColor(R.color.backGroundBtn2));
//        ll.addView(loadLabelBtn);

        noLabelListInShow = 1;
//        loadLabelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                labelsInShow = labelRealm.where(LabelsList.class).equalTo("noList_", noLabelListInShow).findFirst();
//                for (int i = 0; i < labelsInShow.getLabels().size(); i++) {
//                    CheckBox chb = new CheckBox(getApplicationContext());
////                    chb.setText(loadLabels().get(i).getLabels());
//                    chb.setText(labelsInShow.getLabels().get(i).getLabels());
//                    ll.addView(chb);
//                    chItems.add(chb);
//                }
//            }
//        });

        percentFab = findViewById(R.id.percent_fab);
        percentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackOfData pd = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                LabelsList lal = labelRealm.where(LabelsList.class).equalTo("noList_", noLabelListInShow).findFirst();

                for (int i = 0; i < lal.getLabels().size(); i++)
                    noEachLabelSelected.add(0);
                for (int i = 0; i < lal.getLabels().size(); i++) {
                    for (int j = 0; j < pd.getMasUsrLabelsSelected().size(); j++) {
                        Log.i("all labels", lal.getLabels().get(i).getLabels() + " | " + pd.getMasUsrLabelsSelected().get(j).getMLabels() + " | " + pd.getMasUsrLabelsSelected().size());
                        if (lal.getLabels().get(i).getLabels().equals(pd.getMasUsrLabelsSelected().get(j).getMLabels())) {
                            int temp = noEachLabelSelected.remove(i);
                            Log.i("temp=", temp + " " + noEachLabelSelected.size());
                            temp++;
                            noEachLabelSelected.add(i,temp);
                        }
                    }
                }
                for (int i = 0; i < lal.getLabels().size(); i++) {
                    float f = noEachLabelSelected.get(i) / pd.getMasUsrLabelsSelected().size();
                    Log.i("noels=", noEachLabelSelected.get(i) + "");
                    int resPercent = (int) Math.floor(f * 100);
                    tvItems.get(i).setText("   " + resPercent + "%");
                }
            }
        });

        loadFab = findViewById(R.id.Load_fab);
        loadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClickCnt++;
                if (loadClickCnt == 1) {
                    labelsInShow = labelRealm.where(LabelsList.class).equalTo("noList_", noLabelListInShow).findFirst();
                    for (int i = 0; i < labelsInShow.getLabels().size(); i++) {
                        LinearLayout lnl = new LinearLayout(getApplicationContext());
                        lnl.setOrientation(LinearLayout.HORIZONTAL);
                        CheckBox chb = new CheckBox(getApplicationContext());
                        TextView txtv = new TextView(getApplicationContext());
                        txtv.setId(View.generateViewId());
                        txtv.setText("   0%");

//                    chb.setText(loadLabels().get(i).getLabels());
                        chb.setText(labelsInShow.getLabels().get(i).getLabels());
                        txtv.setTag(chb.getText().toString());
                        lnl.addView(chb);
                        lnl.addView(txtv);
                        ll.addView(lnl);
//                        PackOfData pd = dataRealm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
//                        pd.getMasUsrLabelsSelected();
                        chItems.add(chb);
                        tvItems.add(txtv);
                    }
                }
            }
        });
//        FloatingActionButton tagFab =

//        Intent i = getIntent();
        String userType /*= i.getExtras().getString("userType")*/;
        userType = "Master";
        //click this button after that select your checkbox to save labels in
        // database.
//        saveSelectedLabelsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                checkedLabels.clear();
//                int j = 0;
//                for (int i = 0; i < chItems.size(); i++) {
//                    if (chItems.get(i).isChecked()) {
////                        mydb.insertData(chItems.get(i).getText().toString());
//                        checkedLabels.add(chItems.get(i).getText().toString());
//                        j++;
//                    }
//                }
//                hitLabels(userType);
//            }
//        });
        tagFab = findViewById(R.id.Tag_fab);
        tagFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagClickCnt++;
                if (tagClickCnt == 1) {
                    checkedLabels.clear();
                    int j = 0;
                    for (int i = 0; i < chItems.size(); i++) {
                        if (chItems.get(i).isChecked()) {
//                        mydb.insertData(chItems.get(i).getText().toString());
                            checkedLabels.add(chItems.get(i).getText().toString());
                            j++;
                        }
                    }
                    hitLabels(userType);
                    Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
//                        int noGroup = 0;
                        for (PackOfData dt : res.getDataQ()) {
                            items.add(dt);
//                            for (int i = 0; i < dt.getImageUri().size(); i++) {

//                                mydb.insertData("image", dt.getImageUri()[i], noGroup);
//                            }
//                            for (int i = 0; i < dt.getVideoUri().length; i++) {
//                                mydb.insertData("video", dt.getVideoUri()[i], noGroup);
//                            }
//                            for (int i = 0; i < dt.getText().length; i++) {
//                                mydb.insertData("text", dt.getText()[i], noGroup);
//                            }
//                            noGroup++;
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

    private void hitLabels(String userType) {
        PackOfData pod = new PackOfData();
        for (int i = 0; i < checkedLabels.size(); i++) {
            if (userType == "Common") {
//                dataRealm.beginTransaction();
                CommonUserLabelSelected culs = new CommonUserLabelSelected();
                culs.setCLabels(checkedLabels.get(i));
//                final CommonUserLabelSelected managedCuls = dataRealm.copyToRealmOrUpdate(culs);
//                PackOfData pod = new PackOfData();
                pod.getComUsrLabelsSelected().add(culs);
//                NoPack nt = new NoPack();
//                nt.setNoPack(noPackInShow);
//                final NoPack managedNoPackT = dataRealm.copyToRealmOrUpdate(nt);
//                pod.noPack_ = noPackInShow;
//                dataRealm.copyToRealmOrUpdate(pod);
//                dataRealm.commitTransaction();
            }

            if (userType == "Master") {
//                dataRealm.beginTransaction();
                MasterUserLabelSelected muls = new MasterUserLabelSelected();
                muls.setMLabels(checkedLabels.get(i));
//                final MasterUserLabelSelected managedMuls = dataRealm.copyToRealmOrUpdate(muls);

                pod.getMasUsrLabelsSelected().add(muls);
//                NoPack nt = new NoPack();
//                nt.setNoPack(noPackInShow);
//                final NoPack managedNoPackT = dataRealm.copyToRealmOrUpdate(nt);
//                pod.setNoPack(nt);
//                pod.noPack_ = noPackInShow;
//                dataRealm.copyToRealmOrUpdate(pod);
//                dataRealm.commitTransaction();
            }
        }
        pod.noPack_ = noPackInShow;
        dataRealm.beginTransaction();
        dataRealm.copyToRealmOrUpdate(pod);
        dataRealm.commitTransaction();
    }

    private ArrayList<Labels_> loadLabels() {
        final ArrayList<Labels_> items = new ArrayList<>();

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
                        for (Labels_ labels : res.getLabelQ()) {
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

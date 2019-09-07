package com.example.labeling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;
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

    private ViewPager viewPager;
//    private DataListResponse res;
//    private CircleIndicator circleIndicator;

    FragmentsPagerAdapter mAdapter;

    private ArrayList<CheckBox> chItems;
    private String[] label;
    private int noPackInShow;
    public PackOfData packInShow;
    private Realm realm;
    public int flagForChangePack = 0;
    public static ArrayList<String> imageUrlFromDB = new ArrayList<String>();
    public static ArrayList<String> textFromDB = new ArrayList<String>();
    public static ArrayList<String> videoUrlFromDB = new ArrayList<String>();
    private String[] imageUrlF;
    static BottomNavigationView bottomNavigation;
    List<Fragment> fragments;

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
                .name("mRealm.realm")
                .schemaVersion(3)
                .build();
        realm = Realm.getInstance(myconfig);
        realm.beginTransaction();
        /*
        first PackOfData that will be save in database.
         */
        RealmList<Text_> t1 = new RealmList<>();
        RealmList<ImageUri_> i1 = new RealmList<>();
        RealmList<VideoUri_> v1 = new RealmList<>();
        RealmList<Labels_> l1 = new RealmList<>();

        Text_ mt1 = new Text_();
        mt1.setText("my first text in pack to show");
        final Text_ managedText1 = realm.copyToRealm(mt1); // Persist unmanaged objects
        t1.add(mt1);

        ImageUri_ mi1 = new ImageUri_();
        mi1.setImageUri("https://www.imgurl.ir/uploads/a801954_.jpg");
        final ImageUri_ managedImageUri1 = realm.copyToRealm(mi1); // Persist unmanaged objects
        i1.add(mi1);

        ImageUri_ mi2 = new ImageUri_();
        mi2.setImageUri("https://www.imgurl.ir/uploads/n061181_.jpg");
        final ImageUri_ managedImageUri2 = realm.copyToRealm(mi2); // Persist unmanaged objects
        i1.add(mi2);

        ImageUri_ mi3 = new ImageUri_();
        mi3.setImageUri("https://www.imgurl.ir/uploads/x5262_.jpg");
        final ImageUri_ managedImageUri3 = realm.copyToRealm(mi3); // Persist unmanaged objects
        i1.add(mi3);

        VideoUri_ mv1 = new VideoUri_();
        mv1.setVideoUri("https://www.imgurl.ir/uploads/t666542_.mp4");
        final VideoUri_ managedVideoUri1 = realm.copyToRealm(mv1); // Persist unmanaged objects
        v1.add(mv1);

        Labels_ ml1 = new Labels_();
        ml1.setLabels("");
        final Labels_ managedLabels1 = realm.copyToRealm(ml1);
        l1.add(ml1);

        NoPack n1 = new NoPack();
        n1.setNoPack(1);
        final NoPack managedNoPack1 = realm.copyToRealm(n1);


//        PackOfData p1 = new PackOfData();
//        p1.setNoPack(1);
//        p1.setLabels(l1);
//        p1.setText(t1);
//        p1.setImageUri(i1);
//        p1.setVideoUri(v1);

        PackOfData packOfData1 = realm.createObject(PackOfData.class); // Create managed objects directly
        packOfData1.getText().add(managedText1);
        packOfData1.setNoPack(managedNoPack1);
        packOfData1.getImageUri().add(managedImageUri1);
        packOfData1.getImageUri().add(managedImageUri2);
        packOfData1.getImageUri().add(managedImageUri3);
        packOfData1.getVideoUri().add(managedVideoUri1);
        packOfData1.getLabels().add(managedLabels1);
        realm.commitTransaction();
//        pODDAO.save(p1, this);
        /*
        second PackOfData that will be save in database
         */
        realm.beginTransaction();
        RealmList<Text_> t2 = new RealmList<>();
        RealmList<ImageUri_> i2 = new RealmList<>();
        RealmList<VideoUri_> v2 = new RealmList<>();
        RealmList<Labels_> l2 = new RealmList<>();

        Text_ mt2 = new Text_();
        mt2.setText("my second text in pack to show");
        final Text_ managedText2 = realm.copyToRealm(mt2); // Persist unmanaged objects
        t2.add(mt1);

        ImageUri_ mi4 = new ImageUri_();
        mi4.setImageUri("https://imgurl.ir/uploads/m961881_.jpg");
        final ImageUri_ managedImageUri4 = realm.copyToRealm(mi4); // Persist unmanaged objects
        i2.add(mi4);

        ImageUri_ mi5 = new ImageUri_();
        mi5.setImageUri("https://imgurl.ir/uploads/b687218_.jpg");
        final ImageUri_ managedImageUri5 = realm.copyToRealm(mi5); // Persist unmanaged objects
        i2.add(mi5);

        ImageUri_ mi6 = new ImageUri_();
        mi6.setImageUri("https://imgurl.ir/uploads/b522675_.jpg");
        final ImageUri_ managedImageUri6 = realm.copyToRealm(mi6); // Persist unmanaged objects
        i2.add(mi6);

        VideoUri_ mv2 = new VideoUri_();
        mv2.setVideoUri("https://imgurl.ir/uploads/y304_.mp4");
        final VideoUri_ managedVideoUri2 = realm.copyToRealm(mv2); // Persist unmanaged objects
        v2.add(mv2);

        Labels_ ml2 = new Labels_();
        ml2.setLabels("");
        final Labels_ managedLabels2 = realm.copyToRealm(ml1);
        l2.add(ml2);

        NoPack n2 = new NoPack();
        n2.setNoPack(2);
        final NoPack managedNoPack2 = realm.copyToRealm(n2);

//        PackOfData p2 = new PackOfData();
//        p2.setNoPack(2);
//        p2.setLabels(l2);
//        p2.setText(t2);
//        p2.setImageUri(i2);
//        p2.setVideoUri(v2);
//        pODDAO.save(p2, this);

        PackOfData packOfData2 = realm.createObject(PackOfData.class); // Create managed objects directly
        packOfData2.getText().add(managedText2);
        packOfData2.setNoPack(managedNoPack2);
        packOfData2.getImageUri().add(managedImageUri4);
        packOfData2.getImageUri().add(managedImageUri5);
        packOfData2.getImageUri().add(managedImageUri6);
        packOfData2.getVideoUri().add(managedVideoUri2);
        packOfData2.getLabels().add(managedLabels2);
        realm.commitTransaction();

        Toast.makeText(this, realm.getPath(), Toast.LENGTH_LONG).show();


//        packInShow = realm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();


        bottomNavigation = findViewById(R.id.nv);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_left:
                        if (noPackInShow > 1) {
//                            flagForChangePack = 2;
                            noPackInShow--;
                            Toast.makeText(getApplicationContext(),noPackInShow +"", Toast.LENGTH_SHORT).show();
                            packInShow = realm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(L)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++){
                                textFromDB.add(packInShow.text.get(i).getText());
                                Log.i("get text from DB(L)", "Done");
                            }
                            videoUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getVideoUri().size(); i++){
                                videoUrlFromDB.add(packInShow.videoUri.get(i).getVideoUri());
                                Log.i("get video from DB(L)", "Done");
                            }

                            mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());
//                            fragmentTransaction = LabelData.this.getSupportFragmentManager().beginTransaction();
                            fragments = mAdapter.getFragments();
                            mAdapter.setFragments(fragments);
                            viewPager.setAdapter(mAdapter);
                        return true;
                        }
                    case R.id.navigation_right:
                        if (noPackInShow < realm.where(PackOfData.class).findAll().size()) {
//                            Toast.makeText(getApplicationContext(),noPackInShow +"", Toast.LENGTH_SHORT).show();
//                            flagForChangePack = 3;
                            noPackInShow++;
                            Toast.makeText(getApplicationContext(),noPackInShow +"", Toast.LENGTH_SHORT).show();
                            packInShow = realm.where(PackOfData.class).equalTo("noPack_", noPackInShow).findFirst();
                            imageUrlFromDB.clear();
                            for (int i = 0; i < packInShow.getImageUri().size(); i++) {
                                imageUrlFromDB.add(packInShow.imageUri.get(i).getImageUri());
                                Log.i("get imageUri from DB(R)", packInShow.imageUri.get(i).getImageUri());
                            }
                            textFromDB.clear();
                            for (int i = 0; i < packInShow.getText().size(); i++){
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
                        return true;
                        }
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
        saveLabelSelectedBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        saveLabelSelectedBtn.setBackgroundColor(getResources().getColor(R.color.backGroundBtn1));
        ll.addView(saveLabelSelectedBtn);
        Button loadLabelBtn = new Button(this);
        loadLabelBtn.setText("load labels from server");
        loadLabelBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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
//                hitLabel(loadData().get(noPackInShow), label);
            }
        });
        myLayout.addView(sv);

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

    private void hitLabel(PackOfData pod, RealmList<Labels_> labelSelected) {
        pod.setLabels(labelSelected);
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

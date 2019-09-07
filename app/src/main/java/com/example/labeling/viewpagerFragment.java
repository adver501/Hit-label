package com.example.labeling;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class viewpagerFragment extends Fragment {
    //    private Context context;
    String imageid;
    String textid;
    String videoid;
    private CoutProgressBar coutProgressBar;
//    public viewpagerFragment(Context context) {
//        this.context = context;
//    }

    @BindView(R.id.myText) TextView tv;
    @BindView(R.id.myImage) ImageView iv;
    @BindView(R.id.myVideo) VideoView vv;
    private Unbinder unbinder;



    public static viewpagerFragment newInstance(String key, String typeOfData) {

        viewpagerFragment fragment = new viewpagerFragment();
        Bundle args = new Bundle();
        if (typeOfData == "image")
            args.putString("imageid", key);
        if (typeOfData == "text")
            args.putString("textid", key);
        if (typeOfData == "video")
            args.putString("videoid", key);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater
                .from(getActivity().getApplicationContext())
                .inflate(R.layout.fragment_view_pager, null);
        unbinder = ButterKnife.bind(this, view);

        imageid = getArguments().getString("imageid");
        textid = getArguments().getString("textid");
        videoid = getArguments().getString("videoid");

        if (imageid != null) {
            iv.setVisibility(View.VISIBLE);
            Picasso.with(getContext())
                    .load(imageid)
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i("success", "image placed");
                        }

                        @Override
                        public void onError() {
                            Log.i("failed", "image not placed");
                        }
                    });
        }
        if (textid != null){
            tv.setVisibility(View.VISIBLE);
            tv.setText(textid);
            Log.i("success", "text placed");
        }
        if (videoid != null){
            vv.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(videoid);
            vv.setVideoURI(uri);
            vv.requestFocus();
            vv.start();
            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

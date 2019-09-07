package com.example.labeling;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labeling.DataStorage.PackOfData;

import java.util.List;

public class MyPager extends PagerAdapter {
    private List<PackOfData> profi;
    private Context context;
    public MyPager(Context context, List<PackOfData> profi) {
        this.context = context;
        this.profi = profi;
    }
    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.image, null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)));
        TextView txtview = view.findViewById(R.id.txt_data_in_Scroll);

        container.addView(view);
        return view;
    }
    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return profi.size();
    }
    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    private int getImageAt(int position) {
        switch (position) {
            case 0:
                return R.drawable.e1;
            case 1:
                return R.drawable.e2;
            case 2:
                return R.drawable.e3;
            case 3:
                return R.drawable.e4;
            default:
                return R.drawable.loadingicon;
        }
    }

    private void isText(List<PackOfData> profi){

    }
}

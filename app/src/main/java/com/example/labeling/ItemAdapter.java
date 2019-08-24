package com.example.labeling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Item> mItem;
    private ListView listView;
    private LayoutInflater inflater;
    private String[] labels;

    public ItemAdapter(Context mContext, ArrayList<Item> mItem) {
        this.mContext = mContext;
        this.mItem = mItem;
        this.inflater = LayoutInflater.from(mContext);
        labels = new String[50];
    }

    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        final LinearLayout parentLinearLayout;
        if (view == null)
            vi = inflater.from(mContext).inflate(R.layout.activity_define_labels, null);

        parentLinearLayout = (LinearLayout) vi.findViewById(R.id.parent_linear_layout);

        Item currentItem = (Item) getItem(i);
        class CustomClickListener implements View.OnClickListener {
            private Item mIt;

            CustomClickListener(Item it) {
                this.mIt = it;
            }

            public void onClick(View v) {
                // The item that was clicked it mIt
                parentLinearLayout.removeView((View) v.getParent());
            }
        }

        Button addLabelBtn = parentLinearLayout.findViewById(R.id.add_field_button);
        Button saveLabelsBtn = parentLinearLayout.findViewById(R.id.save_button);
        Button deleteBtn = parentLinearLayout.findViewById(R.id.delete_button);
        final EditText editTextLabel = parentLinearLayout.findViewById(R.id.editTextLable);

        saveLabelsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labels[2] = editTextLabel.getText().toString();
//                Toast.makeText(mContext, labels[2],Toast.LENGTH_LONG).show();
            }
        });
        addLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.field, null);
                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLinearLayout.removeView((View) view.getParent());
            }
        });
        deleteBtn.setOnClickListener(new CustomClickListener(currentItem));


        return vi;
    }
}
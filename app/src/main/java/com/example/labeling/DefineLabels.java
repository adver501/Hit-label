package com.example.labeling;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DefineLabels extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    private ListView myListView;
    private ArrayList<Item> arrayItem;
//    private RecyclerView myRecyclerView;
//    private List<Item> itemsList = new ArrayList<>();
//    private ItemAdapter iAdapter;
    private Button addLabelBtn, deleteBtn;
    private EditText editTextlabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_labels);
        editTextlabel = findViewById(R.id.editTextLable);
//        myRecyclerView = findViewById(R.id.recycler_view);
//        iAdapter = new ItemAdapter(itemsList);
        myListView = findViewById(R.id.list_view);
        final LinearLayout parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        arrayItem = new ArrayList<>();
        ItemAdapter mAdapter = new ItemAdapter(this, arrayItem);
        arrayItem.add(new Item("add Label", "","", ""));
        myListView.setAdapter(mAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parentLinearLayout.removeView((View) view.getParent());
            }
        });
//        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
//        ArrayAdapter<LinearLayout> mAdapter = new ArrayAdapter<LinearLayout>(this,
//                android.R.layout., parentLinearLayout);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        myRecyclerView.setLayoutManager(mLayoutManager);
//        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        myRecyclerView.setAdapter(iAdapter);
//        addLabelBtn = findViewById(R.id._addLabelBtn);
//        deleteBtn = findViewById(R.id.delete_button);

//        Item item1 = new Item(editTextlabel, addLabelBtn, deleteBtn);
//        itemsList.add(item1);
//        iAdapter.notifyDataSetChanged();
    }

//    public void onAddField(View v) {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View rowView = inflater.inflate(R.layout.field, null);
//        // Add the new row before the add field button.
//        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
//    }
//
//    public void onDelete(View v) {
//        parentLinearLayout.removeView((View) v.getParent());
//    }

}

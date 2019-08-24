package com.example.labeling;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import com.apg.mobile.roundtextview.RoundTextView;

public class _DefineLabels extends AppCompatActivity {

    private Button addLabelBtn;
    private EditText editTextLabel;
    private RoundTextView definedLabel;
    private String textEntered;
    private int numOfBtnClicked;
    private int generatorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__define_labels);
        addLabelBtn = findViewById(R.id._addLabelBtn);
        editTextLabel = findViewById(R.id.editTextLable);
        definedLabel = findViewById(R.id.definedLabel);
//        numOfBtnClicked = 0;

        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainConstraint);
        final ConstraintSet set = new ConstraintSet();
        final ConstraintLayout.LayoutParams roundTextViewParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        roundTextViewParams.setMargins(32, 32, 0, 0);


        addLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numOfBtnClicked++;
                textEntered = editTextLabel.getText().toString();
                if (textEntered != null && numOfBtnClicked == 1) {
                    definedLabel.setVisibility(View.VISIBLE);
                    definedLabel.setText(textEntered);
                    editTextLabel.setText("");
                }
                if (textEntered != null && numOfBtnClicked > 1) {
                    RoundTextView roundTextView = new RoundTextView(getApplicationContext());
                    generatorId = View.generateViewId();
                    roundTextView.setId(generatorId);
//                    roundTextView.setLayoutParams(roundTextViewParams);
                    layout.addView(roundTextView, 0);
                    set.clone(layout);
                    if (numOfBtnClicked == 2)
                        set.connect(roundTextView.getId(), ConstraintSet.TOP, definedLabel.getId(), ConstraintSet.BOTTOM);
                    if (numOfBtnClicked > 2)
                        set.connect(roundTextView.getId(), ConstraintSet.BOTTOM, generatorId, ConstraintSet.BOTTOM);
                    set.applyTo(layout);

                    roundTextView.setText(textEntered);
                    editTextLabel.setText("");
                }
            }
        });

    }
}

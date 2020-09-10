package com.example.qitiansapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OtherTransportationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_transportation_activity);
        Integer distance = getIntent().getExtras().getInt("distance");
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText("In order to travel " + distance + " miles");

    }
}
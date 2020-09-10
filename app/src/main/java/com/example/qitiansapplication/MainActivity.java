package com.example.qitiansapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // map to two decimal places
    private static DecimalFormat df = new DecimalFormat("0.00");
    double speed = 0.0;
    int range = 0;
    double distance = 0;
    double timeFactor = 1;
    HashMap<String, Double> speedMap = new HashMap() {{
        put("Walking", 3.1);
        put("Boosted Mini S Board", 18.0);
        put("Evolve Bamboo GTR 2in1", 24.0);
        put("OneWheel XR", 19.0);
        put("MotoTec Skateboard", 22.0);
        put("Segway Ninebot S", 10.0);
        put("Segway Ninebot S-PLUS", 12.0);
        put("Razor Scooter", 18.0);
        put("GeoBlade 500", 15.0);
        put("Hovertrax Hoverboard", 9.0);
    }};

    HashMap<Integer, Integer> rangeMap = new HashMap() {{
        put("Walking", 30);
        put("Boosted Mini S Board", 7);
        put("Evolve Bamboo GTR 2in1", 31);
        put("OneWheel XR", 18);
        put("MotoTec Skateboard", 10);
        put("Segway Ninebot S", 13);
        put("Segway Ninebot S-PLUS", 22);
        put("Razor Scooter", 15);
        put("GeoBlade 500", 8);
        put("Hovertrax Hoverboard", 6);
    }};

    HashMap<String, Integer> timeMap = new HashMap<String, Integer>() {{
        put("Hours", 1);
        put("Minutes", 60);
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Maps the type of transportation to its speed


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.electic_time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = (String) parent.getItemAtPosition(position);
                speed = speedMap.get(choice);
                range = rangeMap.get(choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        Spinner timeSpinner = (Spinner) findViewById(R.id.timespinner);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = (String) parent.getItemAtPosition(position);
                timeFactor = timeMap.get(choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        Button computeTimeButton = (Button) findViewById(R.id.computeTime);
        computeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText NumEditText = (EditText) findViewById(R.id.distance);
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
                if (distance > range) resultTextView.setText("Exceeded");
                else {
                    distance = Double.parseDouble(NumEditText.getText().toString());
                    double time = speed != 0.0 ? distance / speed : 0.0;
                    resultTextView.setText(df.format(time * timeFactor));
                }
            }
        });

        Button otherTransportationButton = (Button) findViewById(R.id.other_transportation);
        otherTransportationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), OtherTransportationActivity.class);
                startIntent.putExtra("distance", distance);
                startActivity(startIntent);
            }
        });
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
}
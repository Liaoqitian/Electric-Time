package com.example.qitiansapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;

public class TimeInputActivity extends AppCompatActivity {
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static HashMap<Integer, String> stringMap = new HashMap<Integer, String>() {{
        put(1, "Walking");
        put(2, "Boosted Mini S Board");
        put(3, "Evolve Bamboo GTR 2in1");
        put(4, "OneWheel XR");
        put(5, "MotoTec Skateboard");
        put(6, "Segway Ninebot S");
        put(7, "Segway Ninebot S-PLUS");
        put(8, "Razor Scooter");
        put(9, "GeoBlade 500");
        put(10, "Hovertrax Hoverboard");
    }};

    private static HashMap<Integer, Double> speedMap = new HashMap<Integer, Double>() {{
        put(1, 3.1);
        put(2, 18.0);
        put(3, 24.0);
        put(4, 19.0);
        put(5, 22.0);
        put(6, 10.0);
        put(7, 12.0);
        put(8, 18.0);
        put(9, 15.0);
        put(10, 9.0);
    }};

    private static HashMap<Integer, Integer> rangeMap = new HashMap<Integer, Integer>() {{
        put(1, 30);
        put(2, 7);
        put(3, 31);
        put(4, 18);
        put(5, 10);
        put(6, 13);
        put(7, 22);
        put(8, 15);
        put(9, 8);
        put(10, 6);
    }};

    private static HashMap<Integer, Integer> textIDMap = new HashMap<Integer, Integer>() {{
        put(1, R.id.walkingDistance);
        put(2, R.id.boostedDistance);
        put(3, R.id.evolveDistance);
        put(4, R.id.oneWheelDistance);
        put(5, R.id.motoTecDistance);
        put(6, R.id.segwayDistance);
        put(7, R.id.segwayPlusDistance);
        put(8, R.id.razorDistance);
        put(9, R.id.geoBladeDistance);
        put(10, R.id.hovertraxDistance);
    }};

    private static HashMap<Integer, Integer> imageIDMap = new HashMap<Integer, Integer>() {{
        put(1, R.id.walkingImage);
        put(2, R.id.boostedImage);
        put(3, R.id.evolveImage);
        put(4, R.id.oneWheelImage);
        put(5, R.id.motoTecImage);
        put(6, R.id.segwayImage);
        put(7, R.id.segwayPlusImage);
        put(8, R.id.razorImage);
        put(9, R.id.geoBladeImage);
        put(10, R.id.hoverTraxImage);
    }};

    private static HashMap<Integer, Integer> imageMap = new HashMap<Integer, Integer>() {{
        put(1, R.drawable.walking);
        put(2, R.drawable.boosted);
        put(3, R.drawable.evolve);
        put(4, R.drawable.onewheel);
        put(5, R.drawable.mototec);
        put(6, R.drawable.segway);
        put(7, R.drawable.segwayplus);
        put(8, R.drawable.razor);
        put(9, R.drawable.geoblade);
        put(10, R.drawable.hovertrax);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_input);

        for (Integer index: stringMap.keySet()) {
            int imageID = (imageIDMap != null) ? imageIDMap.get(index) : -1;
            int image = (imageMap != null) ? imageMap.get(index) : -1;
            ImageView iv = (ImageView) findViewById(imageID);
            iv.setImageResource(image);
        }
        Button computeDistanceButton = (Button) findViewById(R.id.computeDistance);
        computeDistanceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText NumEditText = (EditText) findViewById(R.id.timeblank);
                double time = Double.parseDouble(NumEditText.getText().toString());

                for (Integer index: stringMap.keySet()) {
                    String transportation = stringMap.get(index);
                    int id = (textIDMap != null) ? textIDMap.get(index) : -1;
                    TextView tv = (TextView)findViewById(id);
                    int range = rangeMap.get(index);
                    double speed = (speedMap != null) ? speedMap.get(index) : -1;
                    double distance = speed * time / 60;
                    distance = (range < distance) ? range : distance;
                    String output = df.format(distance) + "miles";
                    tv.setText(output);
                }
            }
        });
    }
}
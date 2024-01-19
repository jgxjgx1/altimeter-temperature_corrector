package com.altimetercorrector;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * VV BEGINNING VV
 */
public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView corr;
    TextView result;
    EditText inputAlti;
    Button exec_btn;

    // calc vars:
    int altitude_value;
    int total_correction;
    int[] altitudes ={100, 200,300,400, 500,600,700, 800,900,1000, 1500,2000,3000};
    // ---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        corr = (TextView) findViewById(R.id.corr);
        result = (TextView) findViewById(R.id.result);
        radioGroup = findViewById(R.id.radio_group);

        inputAlti = (EditText) findViewById(R.id.inputAlti);
        exec_btn = (Button) findViewById(R.id.exec_btn);
    }

    public void RadioClicked(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "SEL: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();

        /*
         * start:
         */
        if (v.getId() == R.id.radio1) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(0);
                }
            });
        }

        else if (v.getId() == R.id.radio2) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(1);
                }
            });
        }

        else if (v.getId() == R.id.radio3) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(2);
                }
            });
        }

        else if (v.getId() == R.id.radio4) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(3);
                }
            });
        }

        else if (v.getId() == R.id.radio5) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(4);
                }
            });
        }

        else if (v.getId() == R.id.radio6) {
            // clear all and hide the half-deg mode hint /initialise
            initClear();

            // ENTER value
            exec_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    altitude_value = Integer.parseInt(inputAlti.getText().toString());
                    showToast(String.valueOf(altitude_value)+" ft");
                    calcCorrection(5);
                }
            });
        }
    }

    // own functions:
    private void showToast(String text)
    {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void initClear()
    {
        corr.setText(" - - -  * ");
        result.setText(" - - -  * ");
        altitude_value = 0;
        total_correction = 0;

        inputAlti.getText().clear();
        inputAlti.requestFocus();
    }

    private void calcCorrection(int j)
    {
        int[][] table_of_corrections =
        {
            {10, 20,20,30,  30,40,40,    50,50,60,     90,120,170},     // 0 -- 0 C
            {10, 20,30,40,  50,60,70,    80,90,100,   150,200,290},
            {20, 30,50,60,  70,90,100,   120,130,140, 210,280,420},
            {30, 40,60,80,  100,120,140, 150,170,190, 280,380,570},
            {30, 50,80,100, 120,150,170, 190,220,240, 360,480,720},
            {40, 60,90,120, 150,180,210, 240,270,300, 450,590,890}      // 5 -- -50 C
        };

        int n = 12;
        int alt_endsum = altitude_value;

        do{
            if (alt_endsum >= altitudes[n]) {
                total_correction += table_of_corrections[j][n];
                alt_endsum -= altitudes[n];
            }
            else {
                while (alt_endsum < altitudes[n]){
                    n--;
                }
                total_correction += table_of_corrections[j][n];
                alt_endsum -= altitudes[n];
            }
        } while(alt_endsum > 0);

        int req_alti = total_correction+altitude_value;
        corr.setText("|| Total correction: "+ total_correction +" ft ||");
        result.setText("*** Corrected altitude: "+ req_alti +" ft ***");
    }
}
package com.home.currency;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final float UsDollarToTaiwanDollar = 30.9f;
    private EditText editTextNtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewAndSetListener();
    }

    private void findViewAndSetListener() {
        editTextNtd = findViewById(R.id.ntd);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ntd = editTextNtd.getText().toString();

                if (ntd.length() == 0)
                    showProblemMsg();
                else
                    showResult();
            }
        });
    }


    private void showProblemMsg() {
        new AlertDialog.Builder(this)
                .setTitle("Problem")
                .setMessage("Please enter your NTD amount")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showResult() {
        float ntd = 0;

        try {
            ntd = Float.valueOf(editTextNtd.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (ntd <= 0) {
            Toast.makeText(this, "The format isn't correct", Toast.LENGTH_SHORT).show();
            editTextNtd.setText("");
            return;
        }

        float usd = ntd / UsDollarToTaiwanDollar;
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage("USD is "+ usd)
                .setPositiveButton("OK", null)
                .show();
    }

}

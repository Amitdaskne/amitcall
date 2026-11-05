package com.amitphone;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView numberDisplay;
    private String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberDisplay = findViewById(R.id.numberDisplay);

        int[] ids = {
            R.id.key0, R.id.key1, R.id.key2,
            R.id.key3, R.id.key4, R.id.key5,
            R.id.key6, R.id.key7, R.id.key8,
            R.id.key9, R.id.keyStar, R.id.keyHash
        };

        for (int id : ids) {
            TextView key = findViewById(id);
            key.setOnClickListener(v -> {
                number += key.getText().toString();
                numberDisplay.setText(number);
            });
        }

        findViewById(R.id.backspace).setOnClickListener(v -> {
            if (!number.isEmpty()) {
                number = number.substring(0, number.length() - 1);
                numberDisplay.setText(number);
            }
        });

        findViewById(R.id.callButton).setOnClickListener(v -> {
            if (number.isEmpty()) {
                Toast.makeText(this, "Enter a number",
                    Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(
                Intent.ACTION_DIAL,
                Uri.fromParts("tel", number, null)
            );

            startActivity(intent);
        });
    }
}

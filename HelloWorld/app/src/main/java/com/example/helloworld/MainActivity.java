package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tv_count);
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(tvCount.getText().toString());
                tvCount.setText("" + ++count);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(tvCount.getText().toString());
                tvCount.setText("" + --count);
            }
        });
    }
}
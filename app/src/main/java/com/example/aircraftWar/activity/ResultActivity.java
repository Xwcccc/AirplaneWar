package com.example.aircraftWar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftWar.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private int score;
    private TextView scoreText;
    private Button cancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        score = bundle.getInt("score");
        scoreText = findViewById(R.id.scoreText);
        scoreText.setText("游戏结束，本轮得分:"+score+"分!");

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick (View view) {
        Intent intent = new Intent();
        intent.setClass(ResultActivity.this,MainActivity.class);
        startActivity(intent);
    }

}

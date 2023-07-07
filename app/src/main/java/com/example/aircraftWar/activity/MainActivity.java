package com.example.aircraftWar.activity;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftWar.R;
import com.example.aircraftWar.application.ImageManager;
import com.example.aircraftWar.application.game.EasyGame;
import com.example.aircraftWar.application.game.Game;
import com.example.aircraftWar.application.game.HardGame;
import com.example.aircraftWar.application.game.NormalGame;
import com.example.aircraftWar.dao.RecordDao;
import com.example.aircraftWar.dao.RecordImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Game game;
    public static String level = "";
    public static RecordDao recordDao;

    public static int screenWidth;
    public static int screenHeight;

    public void getScreenHW() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        Log.i(TAG, "screenWidth:" + screenWidth);
        screenHeight = dm.widthPixels;
        Log.i(TAG, "screenWidth:" + screenHeight);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Switch stw1 = (Switch)findViewById(R.id.switch1);
        //记得一定要设置按钮监听！！！
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        stw1.setOnCheckedChangeListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                this.level = "easy";
                break;
            case R.id.button2:
                this.level = "normal";
                break;
            case R.id.button3:
                this.level = "hard";
                break;
        }

        if(!level.equals("")){
            switch (level) {
                case "easy":
                    game = new EasyGame(this);
                    recordDao = new RecordImpl("easyRecords.txt",MainActivity.this);
                    break;
                case "hard":
                    game = new HardGame(this);
                    ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
                    recordDao = new RecordImpl("hardRecords.txt",MainActivity.this);
                    break;
                case "normal":
                    game = new NormalGame(this);
                    ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg5);
                    recordDao = new RecordImpl("normalRecords.txt",MainActivity.this);
                    break;
            }
            setContentView(game);
            game.action();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        game.musicOn = isChecked;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            game.x = event.getX();
            game.y = event.getY();
        }
        return true;
    }

}
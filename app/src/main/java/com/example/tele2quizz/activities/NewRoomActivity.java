package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.tele2quizz.R;
import com.google.android.material.button.MaterialButton;

public class NewRoomActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialButton buttonInRoom;
    private ImageButton buttonBackNewRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonInRoom = findViewById(R.id.buttonInRoom);
        buttonInRoom.setOnClickListener(this);
        buttonBackNewRoom = findViewById(R.id.buttonBackNewRoom);
        buttonBackNewRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackNewRoom:
            case R.id.buttonInRoom:
                onBackPressed();
        }
    }
}
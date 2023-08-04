package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tele2quizz.R;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartTestActivity extends AppCompatActivity implements View.OnClickListener{

    private int id;
    private TextView textTitleStartTest, testDescriptionStartTest, textPointsStartTest;
    private ImageButton buttonBackSatrtTest;
    private MaterialButton buttonStartTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        id = Integer.parseInt(getIntent().getStringExtra("ID"));
        textTitleStartTest = findViewById(R.id.textTitleStartTest);
        testDescriptionStartTest = findViewById(R.id.testDescriptionStartTest);
        buttonBackSatrtTest = findViewById(R.id.buttonBackSatrtTest);
        buttonBackSatrtTest.setOnClickListener(this);
        textPointsStartTest = findViewById(R.id.textPointsStartTest);
        buttonStartTest = findViewById(R.id.buttonStartTest);
        buttonStartTest.setOnClickListener(this);
        getRequest();
    }

    private void getRequest(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getTestsIndex("Token " + token, id);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()){
                    ResponseModelClass responseModelClass = response.body();
                    textTitleStartTest.setText(responseModelClass.getName());
                    testDescriptionStartTest.setText(responseModelClass.getDescription());
                    textPointsStartTest.setText(String.valueOf(responseModelClass.getQuestionPoints()) + " баллов");

                }else{
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackSatrtTest:
                buttonBackSatrtTest.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                onBackPressed();
                break;
            case R.id.buttonStartTest:
                Intent intent = new Intent(StartTestActivity.this, TestActivity.class);
                intent.putExtra("ID",String.valueOf(id));
                startActivity(intent);
                break;

        }
    }
}
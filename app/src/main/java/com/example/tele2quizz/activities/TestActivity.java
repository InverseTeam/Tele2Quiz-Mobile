package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tele2quizz.R;
import com.example.tele2quizz.interfaces.RecyclerViewInterface;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.RecyclerViewTestsAdapter;
import com.example.tele2quizz.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private int id, count = 0;
    private TextView textTestName, textTestDescription;
    private RadioButton radioTestOne, radioTestTwo, radioTestThree, radioTestFour;
    private MaterialButton buttonAnswerTest;
    private ImageButton buttonBackTest;
    private List<ResponseModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        id = Integer.parseInt(getIntent().getStringExtra("ID"));
        textTestName = findViewById(R.id.textTestName);
        textTestDescription = findViewById(R.id.textTestDescription);
        radioTestOne = findViewById(R.id.radioTestOne);
        radioTestTwo = findViewById(R.id.radioTestTwo);
        radioTestThree = findViewById(R.id.radioTestThree);
        radioTestFour = findViewById(R.id.radioTestFour);
        buttonAnswerTest = findViewById(R.id.buttonAnswerTest);
        buttonAnswerTest.setOnClickListener(this);
        buttonBackTest = findViewById(R.id.buttonBackTest);
        buttonBackTest.setOnClickListener(this);
        getQuestions();
    }


    private void getQuestions(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<List<ResponseModelClass>> call = APIController.getInstance().getAPI().getTests("Token " + token, id);
        call.enqueue(new Callback<List<ResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ResponseModelClass>> call, Response<List<ResponseModelClass>> response) {
                if (response.isSuccessful()){
                    List<ResponseModelClass> responseRecyclerCourses = response.body();
                    list = responseRecyclerCourses.get(0).getQuestions();
                }else{
                    Log.d("MyLog", response.toString());
                }
            }
            @Override
            public void onFailure(Call<List<ResponseModelClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAnswerTest:
                count++;
                break;
            case R.id.buttonBackTest:
                buttonBackTest.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                onBackPressed();
                break;
        }
    }
}
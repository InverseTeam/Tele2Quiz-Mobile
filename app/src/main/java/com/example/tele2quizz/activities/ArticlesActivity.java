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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener{

    private int id;
    private ImageButton buttonBackArticles;
    private TextView textArticlesActivityContent, textArticlesActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        id = Integer.parseInt(getIntent().getStringExtra("ID"));
        buttonBackArticles = findViewById(R.id.buttonBackArticles);
        buttonBackArticles.setOnClickListener(this);
        textArticlesActivityContent = findViewById(R.id.textArticlesActivityContent);
        textArticlesActivityName = findViewById(R.id.textArticlesActivityName);
        getRequest();
    }

    private void getRequest(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getArticlesIndex("Token " + token, id);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()){
                    ResponseModelClass responseModelClass = response.body();
                    textArticlesActivityName.setText(responseModelClass.getTitle());
                    textArticlesActivityContent.setText(responseModelClass.getContent());

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
            case R.id.buttonBackArticles:
                buttonBackArticles.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                onBackPressed();
                break;
        }
    }
}
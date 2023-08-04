package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tele2quizz.R;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private MaterialButton buttonEntry, buttonDialogForgetPassword;
    private TextView entryTextRegistration, textEntryError, buttonTextForgetPassword;
    private EditText editTextEntryName, editTextEntryPassword;
    private ProgressBar progressEntry;
    private String name, password;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonEntry = findViewById(R.id.buttonEntry);
        buttonEntry.setOnClickListener(this);
        entryTextRegistration = findViewById(R.id.entryTextRegistration);
        entryTextRegistration.setOnClickListener(this);
        editTextEntryName = findViewById(R.id.editTextEntryName);
        editTextEntryName.setOnFocusChangeListener(this);
        editTextEntryPassword = findViewById(R.id.editTextEntryPassword);
        editTextEntryPassword.setOnFocusChangeListener(this);
        textEntryError = findViewById(R.id.textEntryError);
        progressEntry = findViewById(R.id.progressEntry);
        entryTextRegistration = findViewById(R.id.entryTextRegistration);
        entryTextRegistration.setOnClickListener(this);
        buttonTextForgetPassword = findViewById(R.id.buttonTextForgetPassword);
        buttonTextForgetPassword.setOnClickListener(this);
        editInputText();
    }

    private void checkInput(){
        name = editTextEntryName.getText().toString();
        password = editTextEntryPassword.getText().toString();
        if (name.isEmpty()){
            editTextEntryName.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (password.isEmpty()){
            editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (!(name.isEmpty()) && !(password.isEmpty())){
            progressEntry.setVisibility(View.VISIBLE);
            buttonEntry.setVisibility(View.INVISIBLE);
            postRequest();
        }
    }

    private void postRequest(){
        Call<ResponseModelClass> call = APIController.getInstance()
                .getAPI()
                .postEntry(name, password);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    ResponseModelClass responseModelClass = response.body();
                    Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                    saveToken(responseModelClass.getAuthToken());
                    progressEntry.setVisibility(View.GONE);
                    buttonEntry.setVisibility(View.VISIBLE);
                    entryIntent();
                }else {
                    Log.d("MyLog", response.toString());
                    progressEntry.setVisibility(View.GONE);
                    buttonEntry.setVisibility(View.VISIBLE);
                    textEntryError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
                progressEntry.setVisibility(View.GONE);
                buttonEntry.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonEntry:
                checkInput();
                break;
            case R.id.entryTextRegistration:
                Intent intent = new Intent(EntryActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonTextForgetPassword:
                dialog();
                break;
        }
    }

    private void dialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        buttonDialogForgetPassword = dialog.findViewById(R.id.buttonDialogForgetPassword);
        buttonDialogForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void entryIntent(){
        Intent intent = new Intent(EntryActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("saveToken", MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("tokenSave", token);
        editor.apply();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.editTextEntryName:
            case R.id.editTextEntryPassword:
                textEntryError.setVisibility(View.GONE);
                editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
        }
    }

    private void editInputText(){
        editTextEntryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextEntryName.getText().length() >= 0) {
                    editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                    editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
                    textEntryError.setVisibility(View.GONE);

                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextEntryPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextEntryPassword.getText().length() >= 0) {
                    editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                    editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
                    textEntryError.setVisibility(View.GONE);
                }
            }
        });
    }
}
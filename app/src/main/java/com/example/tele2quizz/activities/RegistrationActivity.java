package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tele2quizz.R;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, RadioGroup.OnCheckedChangeListener {

    private TextView buttonTextRegistrationHaveAccount, textRegistrationNicknameError, textRegistrationErrorPhoneNumber,
            textRegistrationPasswordError, textRegistrationRadioError;
    private MaterialButton buttonRegistration;
    private EditText editTextRegistrationSurname, editTextRegistrationName,
            editTextRegistrationPassword, editTextRegistrationNickname, editTextRegistrationPhoneNumber;
    private RadioButton radioRegistrationOne, radioRegistrationTwo;
    private RadioGroup radioGroupRegistration;
    private String surname, name, nickname, password, phone;
    private ProgressBar progressBar;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonTextRegistrationHaveAccount = findViewById(R.id.buttonTextRegistrationHaveAccount);
        buttonTextRegistrationHaveAccount.setOnClickListener(this);
        buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(this);
        textRegistrationNicknameError = findViewById(R.id.textRegistrationNicknameError);
        textRegistrationErrorPhoneNumber = findViewById(R.id.textRegistrationErrorPhoneNumber);
        textRegistrationPasswordError = findViewById(R.id.textRegistrationPasswordError);
        editTextRegistrationNickname = findViewById(R.id.editTextRegistrationNickname);
        editTextRegistrationNickname.setOnFocusChangeListener(this);
        editTextRegistrationPhoneNumber = findViewById(R.id.editTextRegistrationPhoneNumber);
        editTextRegistrationPhoneNumber.setOnFocusChangeListener(this);
        editTextRegistrationPassword = findViewById(R.id.editTextRegistrationPassword);
        editTextRegistrationPassword.setOnFocusChangeListener(this);
        editTextRegistrationName = findViewById(R.id.editTextRegistrationName);
        editTextRegistrationName.setOnFocusChangeListener(this);
        editTextRegistrationSurname = findViewById(R.id.editTextRegistrationSurname);
        editTextRegistrationSurname.setOnFocusChangeListener(this);
        textRegistrationRadioError = findViewById(R.id.textRegistrationRadioError);
        radioRegistrationOne = findViewById(R.id.radioRegistrationOne);
        radioRegistrationTwo = findViewById(R.id.radioRegistrationTwo);
        radioGroupRegistration = findViewById(R.id.radioGroupRegistration);
        radioGroupRegistration.setOnCheckedChangeListener(this);
        progressBar = findViewById(R.id.progressRegistration);
        editInputText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonTextRegistrationHaveAccount:
                onBackPressed();
                break;
            case R.id.buttonRegistration:
                checkRegistration();
                break;
        }
    }

    private void editInputText(){
        editTextRegistrationName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationName.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationSurname.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }
        });
        editTextRegistrationPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationPhoneNumber.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationNickname.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationPassword.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
    }

    @SuppressLint("ResourceType")
    private void checkRegistration(){
        name = editTextRegistrationName.getText().toString();
        surname = editTextRegistrationSurname.getText().toString();
        password = editTextRegistrationPassword.getText().toString();
        nickname = editTextRegistrationNickname.getText().toString();
        phone = editTextRegistrationPhoneNumber.getText().toString();
        if (name.isEmpty()){
            editTextRegistrationName.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (surname.isEmpty()){
            editTextRegistrationSurname.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (password.isEmpty() || password.length() < 8){
            editTextRegistrationPassword.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationPasswordError.setVisibility(View.VISIBLE);
        }
        if (nickname.isEmpty()){
            editTextRegistrationNickname.setBackgroundResource(R.drawable.edit_text_background_error);
            editTextRegistrationNickname.setVisibility(View.VISIBLE);
        }
        if (phone.isEmpty() || !(android.util.Patterns.PHONE.matcher(phone).matches())){
            editTextRegistrationPhoneNumber.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationErrorPhoneNumber.setVisibility(View.VISIBLE);
        }
        if (!radioRegistrationOne.isChecked() && !radioRegistrationTwo.isChecked()){
            textRegistrationRadioError.setVisibility(View.VISIBLE);
        }
        else if (!(name.isEmpty()) && !(surname.isEmpty()) && !(password.isEmpty()) && !(nickname.isEmpty()) && password.length() > 8
                && !(phone.isEmpty()) && android.util.Patterns.PHONE.matcher(phone).matches() && (radioRegistrationOne.isChecked() || radioRegistrationTwo.isChecked())){
            progressBar.setVisibility(View.VISIBLE);
            buttonRegistration.setVisibility(View.INVISIBLE);
            if (radioRegistrationOne.isChecked()){
                age = 1;
            }else{
                age = 2;
            }
            postRequest();
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.editTextRegistrationName:
            case R.id.editTextRegistrationSurname:
            case R.id.editTextRegistrationNickname:
            case R.id.editTextEntryPassword:
            case R.id.editTextRegistrationPhoneNumber:
                notErrorEdit();
        }
    }

    private void postRequest(){
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().postRegistration(nickname, name, surname, age,phone, password);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    postToken();
                    progressBar.setVisibility(View.GONE);
                    buttonRegistration.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                    entryIntent();
                }else {
                    Log.d("MyLog", response.toString());
                    textRegistrationNicknameError.setVisibility(View.VISIBLE);
                    textRegistrationNicknameError.setText("Пользователь с таким никнейомом уже существует");
                    editTextRegistrationNickname.setBackgroundResource(R.drawable.edit_text_background_error);
                    progressBar.setVisibility(View.GONE);
                    buttonRegistration.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Log.d("MyLog", t.getMessage());
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                buttonRegistration.setVisibility(View.VISIBLE);
            }
        });
    }

    private void postToken(){
        Call<ResponseModelClass> call = APIController.getInstance()
                .getAPI()
                .postEntry(nickname, password);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    ResponseModelClass responseModelClass = response.body();
                    saveToken(responseModelClass.getAuthToken());
                }else {
                    Toast.makeText(getApplicationContext(), "Ошибка: попробуйте ещё раз", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("saveToken", MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("tokenSave", token);
        editor.apply();
        SharedPreferences sharedPreferences2 = getSharedPreferences("saveAgeCategory", MODE_PRIVATE);
        SharedPreferences.Editor editor2 =sharedPreferences2.edit();
        editor2.putInt("ageCategorySave", age);
        editor2.apply();
    }

    private void notErrorEdit(){
        editTextRegistrationName.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationSurname.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationPhoneNumber.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationNickname.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationPassword.setBackgroundResource(R.drawable.edit_text_background);
        textRegistrationNicknameError.setVisibility(View.GONE);
        textRegistrationPasswordError.setVisibility(View.GONE);
        textRegistrationErrorPhoneNumber.setVisibility(View.GONE);
        textRegistrationRadioError.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistrationActivity.this, EntryActivity.class);
        startActivity(intent);
        finish();
    }

    private void entryIntent(){
        Intent intent = new Intent(RegistrationActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioRegistrationTwo:
            case R.id.radioRegistrationOne:
                notErrorEdit();
            break;
        }
    }
}
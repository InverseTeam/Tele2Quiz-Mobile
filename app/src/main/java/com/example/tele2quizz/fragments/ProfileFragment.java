package com.example.tele2quizz.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tele2quizz.R;
import com.example.tele2quizz.activities.EntryActivity;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private MaterialButton buttonExit, buttonExitDialog, buttonNoDialog, buttonSaveProfile, buttonConvert;
    private Dialog dialog;
    private ProgressBar progressProfile;
    private LinearLayout mainLayoutProfile;
    private RadioButton radioProfileOne, radioProfileTwo;
    private int age, points;
    private TextView textPointsProfile, textNicknameProfile, textPhoneProfile, textNameProfile, textSurnameProfile, textGigabyteProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveAgeCategory", MODE_PRIVATE);
        int a = sPref1.getInt("ageCategorySave", 0);
        buttonExit = getActivity().findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(this);
        textPointsProfile = getActivity().findViewById(R.id.textPointsProfile);
        textNicknameProfile = getActivity().findViewById(R.id.textNicknameProfile);
        textPhoneProfile = getActivity().findViewById(R.id.textPhoneProfile);
        textNameProfile = getActivity().findViewById(R.id.textNameProfile);
        textSurnameProfile = getActivity().findViewById(R.id.textSurnameProfile);
        progressProfile = getActivity().findViewById(R.id.progressProfile);
        buttonSaveProfile = getActivity().findViewById(R.id.buttonSaveProfile);
        buttonSaveProfile.setOnClickListener(this);
        buttonConvert = getActivity().findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(this);
        mainLayoutProfile = getActivity().findViewById(R.id.mainLayoutProfile);
        textGigabyteProfile = getActivity().findViewById(R.id.textGigabyteProfile);
        radioProfileOne = getActivity().findViewById(R.id.radioProfileOne);
        radioProfileTwo = getActivity().findViewById(R.id.radioProfileTwo);
        if (a == 1){
            radioProfileOne.setChecked(true);
        }else {
            radioProfileTwo.setChecked(true);
        }
        getAccount();
    }

    private void getAccount(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getMeAccount("Token " + token);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    ResponseModelClass responseModelClass = response.body();
                    textNicknameProfile.setText(responseModelClass.getNickname());
                    textPhoneProfile.setText(responseModelClass.getPhone_number());
                    textNameProfile.setText(responseModelClass.getFirstname());
                    textSurnameProfile.setText(responseModelClass.getLastname());
                    points = responseModelClass.getPoints();
                    textPointsProfile.setText(points + " баллов");
                    age = responseModelClass.getAge();
                    double d =  points*100/30000D;
                    textGigabyteProfile.setText(Double.parseDouble(String.format("%.2f", d).replace(',', '.')) + " ГБ");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    progressProfile.setVisibility(View.GONE);
                    mainLayoutProfile.setVisibility(View.VISIBLE);
                }else {
                    progressProfile.setVisibility(View.GONE);
                    Log.d("MyLog", response.toString());
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету" , Toast.LENGTH_SHORT).show();
                progressProfile.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonExit:
                dialog();
                break;
            case R.id.buttonSaveProfile:
                int b = 0;
                if (radioProfileOne.isChecked()){
                    b = 1;
                }else {
                    b = 2;
                }
                saveToken(b);
                break;
            case R.id.buttonConvert:
                convertCount();
                break;
        }
    }

    private void convertCount(){
        patchRequest();
        points = 0;
        textPointsProfile.setText(points + " баллов");
        double d =  points*100/30000D;
        textGigabyteProfile.setText(Double.parseDouble(String.format("%.2f", d).replace(',', '.')) + " ГБ");
        Toast.makeText(getActivity(), "Гигабайты зачислены на ваш счёт", Toast.LENGTH_SHORT).show();
    }

    private void patchRequest(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().nullPoints("Token " + token);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (!response.isSuccessful()) {
                    Log.d("MyLog", response.toString());
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету" , Toast.LENGTH_SHORT).show();
                progressProfile.setVisibility(View.GONE);
            }
        });
    }

    private void saveToken(int b){
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("saveAgeCategory", MODE_PRIVATE);
        SharedPreferences.Editor editor2 =sharedPreferences2.edit();
        editor2.putInt("ageCategorySave", b);
        editor2.apply();
        Toast.makeText(getActivity(), "Категория изменена", Toast.LENGTH_SHORT).show();
    }

    private void dialog(){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_out_account);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        buttonExitDialog = dialog.findViewById(R.id.buttonExitDialog);
        buttonExitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("tokenSave", "");
                editor.apply();
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                startActivity(intent);
            }
        });
        buttonNoDialog = dialog.findViewById(R.id.buttonNoDialog);
        buttonNoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}
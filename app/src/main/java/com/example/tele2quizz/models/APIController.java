package com.example.tele2quizz.models;

import com.example.tele2quizz.interfaces.APISet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController {

    private static final String URL = "https://tele2quiz.ru/";
    private static APIController clientObject;
    private static Retrofit retrofit;

    public APIController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized APIController getInstance(){
        if (clientObject == null){
            clientObject = new APIController();
        }
        return clientObject;
    }

    public APISet getAPI(){
        return retrofit.create(APISet.class);
    }

}

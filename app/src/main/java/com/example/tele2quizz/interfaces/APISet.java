package com.example.tele2quizz.interfaces;

import com.example.tele2quizz.models.ResponseModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APISet {

    @FormUrlEncoded
    @POST("api/users/auth/token/login/")
    Call<ResponseModelClass> postEntry(
            @Field("nickname") String nickname,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/users/auth/users/")
    Call<ResponseModelClass> postRegistration(
            @Field("nickname") String nickname,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("age") int age,
            @Field("phone_number") String phoneNumber,
            @Field("password") String password
    );

    @GET("api/users/auth/users/me/")
    Call<ResponseModelClass> getMeAccount(@Header("Authorization") String token);

    @GET("api/articles/")
    Call<List<ResponseModelClass>> getArticles(@Header("Authorization") String token);

    @GET("api/quizzes/filter/")
    Call<List<ResponseModelClass>> getTests(@Header("Authorization") String token, @Query("quiz_type") int one);

    @GET("api/articles/{Id}/")
    Call<ResponseModelClass> getArticlesIndex(
            @Header("Authorization") String token,
            @Path("Id") int id
    );
    @GET("api/quizzes/{Id}/")
    Call<ResponseModelClass> getTestsIndex(
            @Header("Authorization") String token,
            @Path("Id") int id
    );

    @PATCH("api/users/me/reset_points/")
    Call<ResponseModelClass> nullPoints(@Header("Authorization") String token);
}

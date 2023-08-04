package com.example.tele2quizz.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tele2quizz.activities.ArticlesActivity;
import com.example.tele2quizz.R;
import com.example.tele2quizz.interfaces.RecyclerViewInterface;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.RecyclerViewArticlesAdapter;
import com.example.tele2quizz.models.ResponseModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesFragment extends Fragment {

    private RecyclerView recyclerArticles;
    private RecyclerViewArticlesAdapter adapter;
    private List<ResponseModelClass> responseRecyclerArticles;
    private LinearLayout linearRecyclerArticles;
    private ProgressBar progressArticles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        recyclerArticles = getActivity().findViewById(R.id.recyclerArticles);
        recyclerArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
        responseRecyclerArticles = new ArrayList<>();
        linearRecyclerArticles = getActivity().findViewById(R.id.linearRecyclerArticles);
        progressArticles = getActivity().findViewById(R.id.progressArticles);
        Call<List<ResponseModelClass>> call = APIController.getInstance().getAPI().getArticles("Token " + token);
        call.enqueue(new Callback<List<ResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ResponseModelClass>> call, Response<List<ResponseModelClass>> response) {
                if (response.isSuccessful()){
                    responseRecyclerArticles = response.body();
                    adapter = new RecyclerViewArticlesAdapter(responseRecyclerArticles, getActivity(), new RecyclerViewInterface() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), ArticlesActivity.class);
                            intent.putExtra("ID", responseRecyclerArticles.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    recyclerArticles.setAdapter(adapter);
                    progressArticles.setVisibility(View.GONE);
                    linearRecyclerArticles.setVisibility(View.VISIBLE);
                    if (responseRecyclerArticles.size() == 0){
                        linearRecyclerArticles.setVisibility(View.GONE);
                    }
                }else{
                    progressArticles.setVisibility(View.GONE);
                    Log.d("MyLog", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModelClass>> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
                progressArticles.setVisibility(View.GONE);
            }
        });
    }


}
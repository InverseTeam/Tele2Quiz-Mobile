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

import com.example.tele2quizz.R;
import com.example.tele2quizz.activities.StartTestActivity;
import com.example.tele2quizz.interfaces.RecyclerViewInterface;
import com.example.tele2quizz.models.APIController;
import com.example.tele2quizz.models.RecyclerViewEventsAdapter;
import com.example.tele2quizz.models.RecyclerViewTestsAdapter;
import com.example.tele2quizz.models.ResponseModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {

    private RecyclerView recyclerEvents;
    private RecyclerViewEventsAdapter adapter;
    private List<ResponseModelClass> responseRecyclerCourses;
    private ProgressBar progressEvents;
    private LinearLayout linearRecyclerEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        recyclerEvents = getActivity().findViewById(R.id.recyclerEvents);
        recyclerEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        responseRecyclerCourses = new ArrayList<>();
        linearRecyclerEvents = getActivity().findViewById(R.id.linearRecyclerEvents);
        progressEvents = getActivity().findViewById(R.id.progressEvents);
        getAllTests();
    }

    private void getAllTests(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<List<ResponseModelClass>> call = APIController.getInstance().getAPI().getTests("Token " + token, 1);
        call.enqueue(new Callback<List<ResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ResponseModelClass>> call, Response<List<ResponseModelClass>> response) {
                if (response.isSuccessful()){
                    responseRecyclerCourses = response.body();
                    adapter = new RecyclerViewEventsAdapter(responseRecyclerCourses, getActivity(), new RecyclerViewInterface() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), StartTestActivity.class);
                            intent.putExtra("ID", responseRecyclerCourses.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    recyclerEvents.setAdapter(adapter);
                    progressEvents.setVisibility(View.GONE);
                    linearRecyclerEvents.setVisibility(View.VISIBLE);
                }else{
                    Log.d("MyLog", response.toString());
                    progressEvents.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModelClass>> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
                progressEvents.setVisibility(View.GONE);
            }
        });
    }

}
package com.example.tele2quizz.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tele2quizz.activities.NewRoomActivity;
import com.example.tele2quizz.R;
import com.example.tele2quizz.activities.ScannerViewActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RoomsFragment extends Fragment implements View.OnClickListener{

    private FloatingActionButton imageButtonPlusRoom;
    private MaterialButton buttonEntryRooms, buttonScanQr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rooms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        imageButtonPlusRoom = getActivity().findViewById(R.id.imageButtonPlusRoom);
        imageButtonPlusRoom.setOnClickListener(this);
        buttonScanQr = getActivity().findViewById(R.id.buttonScanQr);
        buttonScanQr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonPlusRoom:
                Intent intent = new Intent(getActivity(), NewRoomActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonScanQr:
                startActivity(new Intent(getActivity(), ScannerViewActivity.class));
                break;
        }
    }
}
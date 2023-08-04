package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.tele2quizz.fragments.ArticlesFragment;
import com.example.tele2quizz.R;
import com.example.tele2quizz.fragments.EventsFragment;
import com.example.tele2quizz.fragments.ProfileFragment;
import com.example.tele2quizz.fragments.RoomsFragment;
import com.example.tele2quizz.fragments.TestsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);
        replaceFragment(new EventsFragment());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_events:
                    replaceFragment(new EventsFragment());
                    break;
                case R.id.menu_tests:
                    replaceFragment(new TestsFragment());
                    break;
                case R.id.menu_rooms:
                    replaceFragment(new RoomsFragment());
                    break;
                case R.id.menu_articles:
                    replaceFragment(new ArticlesFragment());
                    break;
                case R.id.menu_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
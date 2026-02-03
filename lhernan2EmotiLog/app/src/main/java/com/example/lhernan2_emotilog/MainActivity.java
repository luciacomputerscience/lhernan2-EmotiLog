package com.example.lhernan2_emotilog;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lhernan2_emotilog.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ArrayList<Emotion> dataList;
    private ListView emotionList;
    private EmotionArrayAdapter emotionAdapter;

    // Make a daily summary object as well

    public void addEmotionToSummary(Emotion emotion) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] emotion_desc = {"Happy", "Sad", "Angry", "Anxious", "Bored", "Excited", "Fine", "Energized"};
        String[] emojis = {"\uD83D\uDE0A", "\uD83D\uDE22", "\uD83D\uDE21", "\uD83D\uDE1F", "\uD83D\uDE12", "\uD83E\uDD29", "\uD83D\uDE10", "\uD83D\uDE06",};

        dataList = new ArrayList<>();
        for (int i = 0; i < emotion_desc.length; i++) {
            dataList.add(new Emotion(emotion_desc[i], emojis[i]));
        }

        emotionList = findViewById(R.id.emotion_list);
        emotionAdapter = new EmotionArrayAdapter(this, dataList);
        emotionList.setAdapter(emotionAdapter);

        /* OG*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });

        /* OG*/
        // Add an emotion to the summary by clicking it
        emotionList.setOnItemClickListener((parent, view, position, id) -> {
            Emotion selectedEmotion = dataList.get(position);

            AddEmotionFragment fragment = AddEmotionFragment.newInstance(selectedEmotion);
            fragment.show(getSupportFragmentManager(), "EDIT_CITY");
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
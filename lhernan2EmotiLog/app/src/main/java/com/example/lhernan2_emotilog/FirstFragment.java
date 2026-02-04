package com.example.lhernan2_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

/* This fragment is the home screen.
* It allows users to pick 8 different emojis and log them to track their emotions throughout the day
* I heavily referenced my code from Lab 3 (Listy City)
*/

public class FirstFragment extends Fragment {
    private EmotionViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Initialize the EmotionViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(EmotionViewModel.class);

        // The emoji picker is a listview with 8 preset, customized emotions
        String[] emotion_desc = {"Happy", "Sad", "Angry", "Anxious", "Bored", "Excited", "Fine", "Energized"};
        String[] emojis = {"\uD83D\uDE0A", "\uD83D\uDE22", "\uD83D\uDE21", "\uD83D\uDE1F", "\uD83D\uDE12", "\uD83E\uDD29", "\uD83D\uDE10", "\uD83D\uDE06",};

        // Populate the list view with all the emotions and format the UI
        ArrayList<Emotion> emotions= new ArrayList<>();

        for (int i = 0; i < emotion_desc.length; i++) {
            emotions.add(new Emotion(emotion_desc[i], emojis[i]));
        }

        TextView header = view.findViewById(R.id.header_text_first_fragment);
        ListView emotion_list = view.findViewById(R.id.emotion_list_view);
        EmotionArrayAdapter emotion_adapter = new EmotionArrayAdapter(requireContext(), emotions);
        emotion_list.setAdapter(emotion_adapter);

        // Add an emotion to the log and update the summary when an emotion list item is clicked
        emotion_list.setOnItemClickListener((parent, v, position, id) -> {
            Emotion e = emotions.get(position);

            header.setText("You're feeling:\n" + e.getName() + "\n" + e.getEmoji());
            viewModel.addEmotion(e);
        });
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Buttons to go back and forth between the screen fragments

        // Go to the log
        view.findViewById(R.id.log_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        // Go to the daily summary
        view.findViewById(R.id.summary_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_ThirdFragment);
            }
        });
    }
}
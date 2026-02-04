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

public class FirstFragment extends Fragment {
    private EmotionViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(EmotionViewModel.class);

        String[] emotion_desc = {"Happy", "Sad", "Angry", "Anxious", "Bored", "Excited", "Fine", "Energized"};
        String[] emojis = {"\uD83D\uDE0A", "\uD83D\uDE22", "\uD83D\uDE21", "\uD83D\uDE1F", "\uD83D\uDE12", "\uD83E\uDD29", "\uD83D\uDE10", "\uD83D\uDE06",};

        ArrayList<Emotion> emotions= new ArrayList<>();

        for (int i = 0; i < emotion_desc.length; i++) {
            emotions.add(new Emotion(emotion_desc[i], emojis[i]));
        }

        TextView header = view.findViewById(R.id.header_text_first_fragment);
        ListView emotion_list = view.findViewById(R.id.emotion_list_view);
        EmotionArrayAdapter emotion_adapter = new EmotionArrayAdapter(requireContext(), emotions);
        emotion_list.setAdapter(emotion_adapter);

        emotion_list.setOnItemClickListener((parent, v, position, id) -> {
            Emotion e = emotions.get(position);

            header.setText("You're feeling:\n" + e.getName() + "\n" + e.getEmoji());
            viewModel.addEmotion(e);
        });
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        view.findViewById(R.id.log_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.summary_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_ThirdFragment);
            }
        });
    }
}
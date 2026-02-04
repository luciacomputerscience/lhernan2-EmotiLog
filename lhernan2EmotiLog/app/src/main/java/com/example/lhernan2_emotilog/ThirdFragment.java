package com.example.lhernan2_emotilog;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

/*
* This fragment displays the summary for emotion counts in a day
* It unpacks the hashmap in ViewModel to access the emotions and their counts
* In the future, I would like to spice up the UI a bit */

public class ThirdFragment extends Fragment {
    private EmotionViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        viewModel = new ViewModelProvider(requireActivity())
                .get(EmotionViewModel.class);

        LinearLayout containerLayout = view.findViewById(R.id.summary_container);

        // Refresh the summary when it's updated
        viewModel.getSummary().observe(getViewLifecycleOwner(), summary -> {
            containerLayout.removeAllViews();

            // Get the total amount of emotions logged
            int total = 0;
            for (int count : summary.values()) {
                total += count;
            }
            // Unpack key-value pairs from the viewModel and insert them into the UI
            for (String key : summary.keySet()) {
                TextView tv = new TextView(getContext());
                double percentage = ((double)summary.get(key)/(double)total) * 100;
                // Add the name, count, and frequency
                tv.setText(key + ": " + summary.get(key) + "/" + total + "   (" + percentage + ")");
                tv.setTextSize(30);
                tv.setPadding(16, 16, 16, 16);
                tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                containerLayout.addView(tv);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Back button to return to emotion picker page
        view.findViewById(R.id.back_btn_summary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this).navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });
    }
}

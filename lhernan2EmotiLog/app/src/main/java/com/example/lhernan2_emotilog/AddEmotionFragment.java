package com.example.lhernan2_emotilog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddEmotionFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(Emotion city);
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.emoti_btn, null);
        EditText editCityName = view.findViewById(R.id.emoji_description);
        EditText editProvinceName = view.findViewById(R.id.emoji);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Unpack the bundle for the cities
        Bundle bundle = getArguments();
        Emotion savedCity; // initialize a saved_city

        // Check if we're dealing with a new city or an old one
        if (bundle != null) {
            savedCity = (Emotion) bundle.getSerializable("city");
        } else {
            savedCity = null;
        }

        // Get the name and province of our old city
        if (savedCity != null) {
            editCityName.setText(savedCity.getName());
            editProvinceName.setText(savedCity.getEmoji());
        }

        return builder
                .setView(view)
                .setTitle("Add/Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {

                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    if (savedCity == null) {
                        // Add a city
                        listener.addCity(new Emotion(cityName, provinceName));
                    } else {
                        // Edit a city
                        savedCity.setName(cityName);
                        savedCity.setEmoji(provinceName);
                    }
                })
                .create();
    }

    static AddEmotionFragment newInstance(Emotion emotion) {
        // Use a bundle to pack up our city for later

        Bundle args = new Bundle();
        args.putSerializable("city", emotion);

        AddEmotionFragment fragment = new AddEmotionFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
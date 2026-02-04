package com.example.lhernan2_emotilog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
* A slightly different format than the EmotionArrayAdapter's UI was needed so I made a separate
* adapter. This one includes a place for the timestamp
* */
public class LogArrayAdapter extends ArrayAdapter<EmotionLog> {
    public LogArrayAdapter(@NonNull Context context, @NonNull ArrayList<EmotionLog> logs) {
        super(context, 0, logs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.log_list_item, parent, false);
        }

        EmotionLog log = getItem(position);

        TextView emojiView = view.findViewById(R.id.log_emoji);
        TextView nameView = view.findViewById(R.id.log_name);
        TextView timeView = view.findViewById(R.id.log_time);

        emojiView.setText(log.getEmotion().getEmoji());
        nameView.setText(log.getEmotion().getName());
        timeView.setText(log.getTimestamp());

        return view;
    }
}

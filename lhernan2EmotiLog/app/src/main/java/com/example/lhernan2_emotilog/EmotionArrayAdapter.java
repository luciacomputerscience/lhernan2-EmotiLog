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

public class EmotionArrayAdapter extends ArrayAdapter<Emotion> {
    public EmotionArrayAdapter(Context context, ArrayList<Emotion> emotions) {
        super(context, 0, emotions);  // 0 is a placeholder since we override getView(), parent doesn't need to know the actual layout
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content_main, parent, false);
        } else {
            view = convertView;
        }
        Emotion emotion = getItem(position);
        TextView emojiDescription = view.findViewById(R.id.emoji_description);
        TextView emojiPicture = view.findViewById(R.id.emoji);
        emojiDescription.setText(emotion.getName());
        emojiPicture.setText(emotion.getEmoji());

        return view;
    }
}

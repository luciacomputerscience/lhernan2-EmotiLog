package com.example.lhernan2_emotilog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


/* The EmotionViewModel helps control the data and data updates,
* Such as adding emotions to the logs or updating the daily summary*/
public class EmotionViewModel extends ViewModel {
    // Create the log and summary
    private final MutableLiveData<ArrayList<EmotionLog>> logs = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<HashMap<String, Integer>> summary = new MutableLiveData<>(new HashMap<>());

    public LiveData<ArrayList<EmotionLog>> getLogs() {
        return logs;
    }

    public LiveData<HashMap<String, Integer>> getSummary() {
        // Storing as a hash map makes it quick to get the count per emotion
        return summary;
    }

    public void addEmotion(Emotion emotion) {
        // Timestamp
        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Update log
        ArrayList<EmotionLog> currentLogs = logs.getValue();
        currentLogs.add(new EmotionLog(emotion, time));
        logs.setValue(currentLogs);

        // Update summary
        HashMap<String, Integer> currentSummary = summary.getValue();
        int count = currentSummary.getOrDefault(emotion.getName(), 0);
        currentSummary.put(emotion.getName(), count + 1);
        summary.setValue(currentSummary);
    }
}

package com.example.lhernan2_emotilog;

/*
* The EmotionLog objects store an emotion and the time it was selected
*/
public class EmotionLog {
    private Emotion emotion;
    private String timestamp;

    public EmotionLog(Emotion emotion, String timestamp) {
        this.emotion = emotion;
        this.timestamp = timestamp;

    }

    public Emotion getEmotion() {
        return emotion;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

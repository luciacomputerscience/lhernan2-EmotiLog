package com.example.lhernan2_emotilog;

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

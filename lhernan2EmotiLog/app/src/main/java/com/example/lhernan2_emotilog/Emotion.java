package com.example.lhernan2_emotilog;
import java.io.Serializable;

/* The emotions class stores an emotion as an object with a name and associated emoji
* to make it easy to pass them around
* In hindsight, I probably could have stored counts and frequencies here too
* but it was good practice for view models
* */

public class Emotion implements Serializable {
    private String name;
    private String emoji;

    public Emotion(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {this.emoji = emoji; }
}

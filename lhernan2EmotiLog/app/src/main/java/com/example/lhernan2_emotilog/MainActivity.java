package com.example.lhernan2_emotilog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/* Since I was using fragments this got left as the default mainActivity
* Holds the app inside it
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
}
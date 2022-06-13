package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



// Let user create a post by taking a photo with their camera
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
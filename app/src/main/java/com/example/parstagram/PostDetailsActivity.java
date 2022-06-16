package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    TextView tvBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvBody=findViewById(R.id.tvBody);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("Posts"));

        tvBody.setText(post.getDescription());
    }
}
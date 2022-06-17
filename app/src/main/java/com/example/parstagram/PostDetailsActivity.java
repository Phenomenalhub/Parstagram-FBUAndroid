package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    TextView tvDescription;
    TextView tvUsername;
    ImageView ivImage;
    TextView tvCreatedAt;
    ImageView ivProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvDescription=findViewById(R.id.tvDescription);
        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        ivProfileImage = findViewById(R.id.ivProfileImage);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("Posts"));

        tvDescription.setText(post.getDescription());
        tvUsername.setText(post.getUser().getUsername());
        tvCreatedAt.setText(post.getCreatedAt().toString());
        ParseFile image = post.getImage();

        ParseFile userImage = post.getUser().getProfileImage();
        Glide.with(this).load(image.getUrl()).into(ivImage);

        if (userImage != null) {
            Glide.with(this).load(userImage.getUrl()).into(ivProfileImage);
        }
    }
}
package com.example.parstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parstagram.fragments.ComposeFragment;
import com.example.parstagram.fragments.PostFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;


// Let user create a post by taking a photo with their camera
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE =  42;
    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;
    Button logOut;
    private Button btnFeeds;
    private File photoFile;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription = findViewById(R.id.etDescription);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        ivPostImage = findViewById(R.id.ivPostImage);
        btnSubmit = findViewById(R.id.btnSubmit);
        logOut = findViewById(R.id.btnLogOut);

//        btnFeeds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
////                startActivity(intent);
//            }
//        });

//        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchCamera();
//            }
//        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentToShow = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        //Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        fragmentToShow = new PostFragment();
                        break;
                    case R.id.action_compose:
                        fragmentToShow = new ComposeFragment();
                        //Toast.makeText(MainActivity.this, "Compose", Toast.LENGTH_SHORT).show();
                        // do something here
                        break;
                    case R.id.action_profile:
                        // do something here
                        fragmentToShow = new ProfileFragment();
                        //Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    default: break;
                }
                if (fragmentToShow != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragmentToShow).commit();
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);



//    private void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.findInBackground((posts, e) -> {
//            if (e != null){
//                Log.e(TAG, "Issue with getting posts", e);
//                return;
//            }
//            for (Post post : posts) {
//                Log.i(TAG, "Post: " + post.getDescription() + ", username:" + post.getUser().getUsername());
//            }
//
//        });
//    }
}
}
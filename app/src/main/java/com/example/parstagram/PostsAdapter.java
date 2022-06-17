package com.example.parstagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    private Context context;
    private List<Post> posts;


    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvlikes;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        private ImageView ivProfileImage;
        private ImageButton ibHeart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ibHeart = itemView.findViewById(R.id.ibHeart);
            tvlikes = itemView.findViewById(R.id.tvlikes);

        }

        public void bind(Post post) {

            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvCreatedAt.setText((post.getCreatedAt().toString()));

            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,PostDetailsActivity.class);
                    i.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                }
            });
            ParseFile image = post.getImage();
            if (image != null) {
                ivImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(image.getUrl()).into(ivImage);
            } else{
                ivImage.setVisibility(View.GONE);
            }

            ParseFile imageProfile = post.getUser().getProfileImage();

            if (imageProfile != null) {
                ivProfileImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(imageProfile.getUrl()).centerCrop().circleCrop().into(ivProfileImage);
            } else{
                ivProfileImage.setVisibility(View.GONE);
            }

            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PostDetailsActivity.class);

                    i.putExtra("Posts", Parcels.wrap(post));
                    context.startActivity(i);
                    //finish();
                }
            });
            ibHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ibHeart.setColorFilter(Color.GRAY);

                    Log.i("test", "button clicks");
                    List<String> likedBy = post.getLikedBy();
                    if (!likedBy.contains(ParseUser.getCurrentUser().getObjectId())){
                        likedBy.add(ParseUser.getCurrentUser().toString());
                        post.setLikedBy(likedBy);
                        //ibHeart.setColorFilter(Color.RED);
                        ibHeart.setBackgroundResource(R.drawable.ic_ufi_heart_active);

                    } else {
                        likedBy.remove(ParseUser.getCurrentUser().getObjectId());
                        post.setLikedBy(likedBy);
                        ibHeart.setColorFilter(Color.GRAY);
                        ibHeart.setBackgroundResource(R.drawable.ufi_heart);
                    }
                    post.saveInBackground();
                    tvlikes.setText(post.likeCountDisplayText());
                }
            });
        }

    }

}

package com.boolokam.boolokamtv.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.jackandphantom.blurimage.BlurImage;
import com.squareup.picasso.Picasso;
import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.entity.Poster;

public class MovieVotesActivity extends AppCompatActivity {
    Poster poster;
    ImageView image_view_activity_movie_cover;
    ImageView image_view_activity_movie_cover_blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_votes);
        init();
    }

    private void init() {
        image_view_activity_movie_cover = findViewById(R.id.image_view_activity_movie_cover);
        image_view_activity_movie_cover_blur = findViewById(R.id.image_view_activity_movie_cover_blur);

        poster = ( Poster) getIntent().getSerializableExtra("poster");
        if(poster!=null){
            setImages();

        }
    }

    void setImages()
    {
        Picasso.with(this).load((poster.getCover()!=null ? poster.getCover() : poster.getImage())).into(image_view_activity_movie_cover);
        Picasso.with(this).load((poster.getCover()!=null ? poster.getCover() : poster.getImage())).into(image_view_activity_movie_cover_blur);
        final com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BlurImage.with(getApplicationContext()).load(bitmap).intensity(20).Async(true).into(image_view_activity_movie_cover_blur);
            }
            @Override
            public void onBitmapFailed(Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        };
        Picasso.with(getApplicationContext()).load(poster.getImage()).into(target);
        //image_view_activity_movie_background.setTag(target);
    }
}
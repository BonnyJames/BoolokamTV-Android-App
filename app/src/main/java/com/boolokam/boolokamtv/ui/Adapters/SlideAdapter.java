package com.boolokam.boolokamtv.ui.Adapters;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.entity.Poster;
import com.boolokam.boolokamtv.ui.activities.MovieActivity;
import com.boolokam.boolokamtv.ui.activities.SerieActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class SlideAdapter  extends  RecyclerView.Adapter<SlideAdapter.SliderViewHolder>{
    private List<Poster> posters = new ArrayList<Poster>();
    private Activity activity;
    private ViewPager2 viewPager;

    public SlideAdapter(Activity activity, List<Poster> postersList, ViewPager2 viewPager2) {
        this.posters = postersList;
        this.activity = activity;
        viewPager = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide_one, parent, false);
        SlideAdapter.SliderViewHolder viewHolder = new SlideAdapter.SliderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

        Picasso.with(activity).load(posters.get(position).getImage()).placeholder(R.drawable.placeholder).into(holder.image_view_item_slide_one);
        holder.text_view_item_slide_one_title.setText(posters.get(position).getTitle());
        redirectToMovie(holder,position);

    }

    void redirectToMovie(SliderViewHolder holder, int position){
        holder.image_view_item_slide_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.image_view_item_slide_one, "imageMain");
                Intent intent = new Intent(activity, MovieActivity.class);
                if (posters.get(position).getType().equals("movie")) {
                    intent = new Intent(activity, MovieActivity.class);
                } else if (posters.get(position).getType().equals("serie")) {
                    intent = new Intent(activity, SerieActivity.class);
                }
                intent.putExtra("poster", posters.get(position));
                activity.startActivity(intent, activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }


    class SliderViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView image_view_item_slide_one;
        TextView text_view_item_slide_one_title;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view_item_slide_one = itemView.findViewById(R.id.image_view_item_slide_one);
            text_view_item_slide_one_title = itemView.findViewById(R.id.text_view_item_slide_one_title);

        }
    }

}

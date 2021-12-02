package com.boolokam.boolokamtv.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.entity.Slide;

import java.util.List;

public class CustomSlideAdapter extends RecyclerView.Adapter<CustomSlideAdapter.SliderViewHolder>{

        private List<Slide> slideList;
        private ViewPager2 viewPager2;
        private Context context;

    CustomSlideAdapter(List<Slide> slideList, ViewPager2 viewPager2, Context context) {
            this.slideList = slideList;
            this.viewPager2 = viewPager2;
            this.context = context;
        }

        @NonNull
        @Override
        public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SliderViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.slide_item_container, parent, false
                    )
            );
        }

        @Override
        public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
            holder.setImage(slideList.get(position), position, context);
            if (position == slideList.size()- 2){
                viewPager2.post(runnable);
            }
        }

        @Override
        public int getItemCount() {
            return slideList.size();
        }

        class SliderViewHolder extends RecyclerView.ViewHolder {
            private RoundedImageView imageView;


            SliderViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageSlide);

            }

            void setImage(Slide sliderItems, int position, Context context){

//use glide or picasso in case you get image from internet
                //imageView.setImageResource(sliderItems.getImage());
                Picasso.with(context).load(slideList.get(position).getImage()).placeholder(R.drawable.placeholder).into(imageView);
            }
        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                slideList.addAll(slideList);
                notifyDataSetChanged();
            }
        };


    }

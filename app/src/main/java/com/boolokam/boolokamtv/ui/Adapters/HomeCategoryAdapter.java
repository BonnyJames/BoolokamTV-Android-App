package com.boolokam.boolokamtv.ui.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.config.Global;
import com.boolokam.boolokamtv.entity.Genre;
import com.boolokam.boolokamtv.ui.activities.GenreActivity;
import com.boolokam.boolokamtv.ui.activities.MyListActivity;
import com.boolokam.boolokamtv.ui.activities.TopActivity;

import java.util.List;
import java.util.Random;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>{
    private List<Genre> genreList;
    private Context context;
    public HomeCategoryAdapter(List<Genre> genreList, Context context){
        this.genreList = genreList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoryAdapter.CategoryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_category, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryBtn.setText(""+genreList.get(position).getTitle());
        holder.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(Global.IS_LOGG)
                    Log.d(Global.TAG,"Movie Genre :"+genreList.get(position).toString());*/
                /*Intent intent = new Intent(context.getApplicationContext(), GenreActivity.class);
                intent.putExtra("genre", genreList.get(position));
                context.startActivity(intent);*/
                if (genreList.get(position).getId() == -1) {
                    Intent intent = new Intent(context.getApplicationContext(), TopActivity.class);
                    intent.putExtra("order", "rating");
                    context.startActivity(intent, ActivityOptionsCompat.makeScaleUpAnimation(v, (int) v.getX(), (int) v.getY(), v.getWidth(), v.getHeight()).toBundle());
                } else if (genreList.get(position).getId() == 0) {
                    Intent intent = new Intent(context.getApplicationContext(), TopActivity.class);
                    intent.putExtra("order", "views");
                    context.startActivity(intent, ActivityOptionsCompat.makeScaleUpAnimation(v, (int) v.getX(), (int) v.getY(), v.getWidth(), v.getHeight()).toBundle());
                } else if (genreList.get(position).getId() == -2) {
                    Intent intent = new Intent(context.getApplicationContext(), MyListActivity.class);
                    context.startActivity(intent, ActivityOptionsCompat.makeScaleUpAnimation(v, (int) v.getX(), (int) v.getY(), v.getWidth(), v.getHeight()).toBundle());
                } else {
                    Intent intent = new Intent(context.getApplicationContext(), GenreActivity.class);
                    intent.putExtra("genre", genreList.get(position));
                    context.startActivity(intent, ActivityOptionsCompat.makeScaleUpAnimation(v, (int) v.getX(), (int) v.getY(), v.getWidth(), v.getHeight()).toBundle());
                }
            }
        });
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (position%4==0)
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.categoryBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_btn1));
            } else {
                holder.categoryBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn1));
            }
        else if(position%4==1)
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.categoryBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_btn2));
            } else {
                holder.categoryBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn2));
            }
        else if(position%4==2)
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.categoryBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_btn3));
            } else {
                holder.categoryBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn3));
            }
        else if(position%4==3)
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.categoryBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_btn4));
            } else {
                holder.categoryBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn4));
            }

    }


    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private Button categoryBtn;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryBtn = itemView.findViewById(R.id.catBtn);
        }
    }
}

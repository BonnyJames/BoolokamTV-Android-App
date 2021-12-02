package com.boolokam.boolokamtv.ui.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.config.Global;
import com.boolokam.boolokamtv.entity.LeaderBoardMovie;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {
    Context context;
    List<LeaderBoardMovie> voteList;

    public LeaderboardAdapter(Context context, List<LeaderBoardMovie> votes) {
        this.context = context;
        this.voteList = votes;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_votes_leaderboard, parent, false);
        LeaderboardViewHolder viewHolder = new LeaderboardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        try {
            if (position == 0) {
                Drawable drawable = context.getResources().getDrawable(R.drawable.bg_circle_first);
                //Typeface typeface = ResourcesCompat.getFont(context, R.font.futura_bold);
                Typeface typeface = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    typeface = context.getResources().getFont(R.font.futura_bold);
                } else {
                    typeface = ResourcesCompat.getFont(context, R.font.futura_bold);
                }
                //Typeface.createFromAsset(context.getAssets(),"futura_bold.ttf");
                holder.numberTV.setTypeface(typeface);
                holder.numberTV.setBackground(drawable);
            } else if (position == 1) {
                Drawable drawable = context.getResources().getDrawable(R.drawable.bg_circle_second);
                holder.numberTV.setBackground(drawable);
                //Typeface typeface = Typeface.createFromAsset(context.getAssets(),"futura_bold.ttf");
                Typeface typeface = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    typeface = context.getResources().getFont(R.font.futura_bold);
                } else {
                    typeface = ResourcesCompat.getFont(context, R.font.futura_bold);
                }
                holder.numberTV.setTypeface(typeface);
            } else if (position == 2) {
                Drawable drawable = context.getResources().getDrawable(R.drawable.bg_circle_third);
                holder.numberTV.setBackground(drawable);
                //Typeface typeface = Typeface.createFromAsset(context.getAssets(),"futura_bold.ttf");
                Typeface typeface = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    typeface = context.getResources().getFont(R.font.futura_bold);
                } else {
                    typeface = ResourcesCompat.getFont(context, R.font.futura_bold);
                }
                holder.numberTV.setTypeface(typeface);
            } else {
                Drawable drawable = context.getResources().getDrawable(R.drawable.bg_circle_transparent);
                holder.numberTV.setBackground(drawable);
            }
            holder.numberTV.setText("" + (position + 1));
            holder.movieNameTV.setText(voteList.get(position).getTitle());
            Picasso.with(context).load(voteList.get(position).getImage()).error(R.drawable.placeholder).into(holder.image_view_activity_movie_cover);
            holder.movieVoteTV.setText("" + voteList.get(position).getNbrVotes());
        }catch (Exception ex)
        {
            if(Global.IS_LOGG)
                ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        TextView numberTV;
        ImageView image_view_activity_movie_cover;
        TextView movieNameTV;
        TextView movieVoteTV;

        public LeaderboardViewHolder(@NonNull View itemView) {

            super(itemView);
            numberTV = itemView.findViewById(R.id.numberTV);
            image_view_activity_movie_cover = itemView.findViewById(R.id.image_view_activity_movie_cover);
            movieNameTV = itemView.findViewById(R.id.movieNameTV);
            movieVoteTV = itemView.findViewById(R.id.movieVoteTV);

        }

    }
}

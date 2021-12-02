package com.boolokam.boolokamtv.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.ui.Adapters.LeaderboardAdapter;

public class LeaderboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LeaderboardAdapter leaderboardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        init();
    }

    private void init() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.leaderBoardRV);
        //getLeaderboard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        /*getMenuInflater().inflate(R.menu.menu_cast, menu);
        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(),
                menu,
                R.id.media_route_menu_item);*/
        return true;
    }



    @Override
    public void onBackPressed(){

         super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }


}
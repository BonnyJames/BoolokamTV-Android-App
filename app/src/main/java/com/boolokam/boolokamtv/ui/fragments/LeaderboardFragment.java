package com.boolokam.boolokamtv.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.Utils.NetworkState;
import com.boolokam.boolokamtv.api.apiClient;
import com.boolokam.boolokamtv.api.apiRest;
import com.boolokam.boolokamtv.config.Global;
import com.boolokam.boolokamtv.entity.Leaderboard;
import com.boolokam.boolokamtv.ui.Adapters.LeaderboardAdapter;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    LinearLayout shareBtnLL;
    TextView competitionEndingTV;
    TextView totalNoOfVotesTV;
    TextView competitionTV;
    RecyclerView leaderBoardRV;
    LeaderboardAdapter leaderboardAdapter;
    SwipeRefreshLayout pullToRefresh;


    public LeaderboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        init();
        getLeaderboard();
        return view;
    }

    void init()
    {
        shareBtnLL = view.findViewById(R.id.shareBtnLL);
        competitionEndingTV = view.findViewById(R.id.competitionEndingTV);
        totalNoOfVotesTV = view.findViewById(R.id.totalNoOfVotesTV);
        leaderBoardRV = view.findViewById(R.id.leaderBoardRV);
        competitionTV = view.findViewById(R.id.competitionTV);
        pullToRefresh = view.findViewById(R.id.pullToRefresh);

        shareBtnLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(NetworkState.isOnline(getContext())) {
                    getLeaderboard(); // your code


                }else
                {
                    Toasty.error(getContext(),"No Internet, Please turn ON your internet");
                }
            }
        });

    }

    void getLeaderboard()
    {
        Retrofit retrofit = apiClient.getClient();
        apiRest service = retrofit.create(apiRest.class);
        Call <List<Leaderboard>> call = service.getAllVotes();
        call.enqueue(new Callback<List<Leaderboard>>() {
            @Override
            public void onResponse(Call<List<Leaderboard>> call, Response<List<Leaderboard>> response) {

                if(response.isSuccessful()){
                    List<Leaderboard> leaderboard = response.body();
                    if(Global.IS_LOGG)
                        Log.d(Global.TAG,"Leaderboard Data :"+leaderboard.toString());
                    if(leaderboard.size()>0)
                    {
                        if(Global.IS_LOGG)
                            Log.d(Global.TAG,"Leaderboard: "+response.body().toString());
                        competitionEndingTV.setText(""+leaderboard.get(0).getEndDate());

                        /*DateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy");
                        SimpleDateFormat d= new SimpleDateFormat("DD MM");
                        try {
                            Date convertedDate = inputFormat.parse(""+leaderboard.get(0).getEndDate());
                            datetime = d.format(convertedDate);

                        }catch (ParseException e)
                        {

                        }*/
                        totalNoOfVotesTV.setText(""+leaderboard.get(0).getNbrVotes());
                        competitionTV.setText(""+leaderboard.get(0).getTitle());

                        if(leaderboard.get(0).getTopMovies().size()>0){
                            leaderboardAdapter = new LeaderboardAdapter(getContext(),leaderboard.get(0).getTopMovies());
                            leaderBoardRV.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                            leaderBoardRV.setAdapter(leaderboardAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getContext());
                            leaderBoardRV.setLayoutManager(layoutManager);
                            leaderBoardRV.setHasFixedSize(true);
                            pullToRefresh.setRefreshing(false);
                        }else
                        {
                            pullToRefresh.setRefreshing(false);
                            //Toasty.error(getContext(), "Votes are zero", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        pullToRefresh.setRefreshing(false);
                        //Toasty.error(getContext(), "Leaderboard is null", Toast.LENGTH_SHORT).show();
                    }




                }else
                {
                    pullToRefresh.setRefreshing(false);
                    if(Global.IS_LOGG)
                        Log.d(Global.TAG,""+response.raw().toString());
                    Toasty.error(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Leaderboard>> call, Throwable t) {
                pullToRefresh.setRefreshing(false);
                if(Global.IS_LOGG) {
                    t.printStackTrace();
                    Toasty.error(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    Log.d(Global.TAG,t.toString());
                }

            }
        });

    }
    void shareApp()
    {
        final String appPackageName=getActivity().getPackageName();
        String shareBody = "Download "+getString(R.string.app_name)+" From :  "+"http://play.google.com/store/apps/details?id=" + appPackageName;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,  getString(R.string.app_name));
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));
    }

}
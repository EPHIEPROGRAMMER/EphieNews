package com.example.stephen.ballislife.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stephen.ballislife.adapters.TeamListAdapter;
import com.example.stephen.ballislife.services.NewsApiService;
import com.example.stephen.ballislife.R;
import com.example.stephen.ballislife.models.Team;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TeamsActivity extends AppCompatActivity {
    public static final String TAG = TeamsActivity.class.getSimpleName();
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private TeamListAdapter mAdapter;

    public ArrayList<Team> mTeams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String team = intent.getStringExtra("team");
        getTeams(team);
    }

    private void getTeams(String team) {
        final NewsApiService newsApiService = new NewsApiService();
        newsApiService.searchTeams(team, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mTeams = newsApiService.processResults(response);

                TeamsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new TeamListAdapter(getApplicationContext(), mTeams);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(TeamsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }



        });
    }

}

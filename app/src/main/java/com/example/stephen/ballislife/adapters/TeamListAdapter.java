package com.example.stephen.ballislife.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stephen.ballislife.R;
import com.example.stephen.ballislife.models.Team;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {
    private ArrayList<Team> mTeams = new ArrayList<>();
    private Context mContext;

    public TeamListAdapter(Context context, ArrayList<Team> teams) {
        mContext = context;
        mTeams = teams;
    }

    @Override
    public TeamListAdapter.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        TeamViewHolder viewHolder = new TeamViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    @Override
    public void onBindViewHolder(TeamListAdapter.TeamViewHolder holder, int position) {
        holder.bindTeam(mTeams.get(position));
    }


    public class TeamViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.teamNameTextView) TextView mNameTextView;
        @Bind(R.id.abbreviationTextView) TextView mAbbreviationTextView;
        @Bind(R.id.conferenceTextView) TextView mConferenceTextView;

        private Context mContext;

        public TeamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindTeam(Team team) {
            mNameTextView.setText(team.getName());
            mAbbreviationTextView.setText(team.getAbbreviation());
            mConferenceTextView.setText(team.getConference());
        }
    }
}
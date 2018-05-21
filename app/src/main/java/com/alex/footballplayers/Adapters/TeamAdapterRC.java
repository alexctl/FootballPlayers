package com.alex.footballplayers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.footballplayers.Activity.PlayersList;
import com.alex.footballplayers.Model.LeagueModel;
import com.alex.footballplayers.Model.TeamModel;
import com.alex.footballplayers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapterRC extends RecyclerView.Adapter<TeamAdapterRC.ViewHolder>{

    private List<TeamModel> teamModels;
    private Context context;

    public TeamAdapterRC(List<TeamModel> teamModels, Context context){
        this.teamModels = teamModels;
        this.context = context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView team_name;
        public ImageView team_image;
        public View mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            mainView = itemView;

            team_name     =  itemView.findViewById(R.id.team_name);
            team_image    =  itemView.findViewById(R.id.team_picture);
        }
    }


    @Override
    public TeamAdapterRC.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_cell,parent,false);
        return new TeamAdapterRC.ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TeamModel teamModel = teamModels.get(position);

        holder.team_name.setText(teamModel.getTeam_name());

        Picasso.with(context)
                .load(teamModel.getTeam_image_url())
                .into(holder.team_image);

        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playersList = new Intent(context, PlayersList.class);
                playersList.putExtra("teamID", teamModels.get(position).getTeam_id());
                playersList.putExtra("teamName", teamModels.get(position).getTeam_name());
                context.startActivity(playersList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamModels.size();
    }
}
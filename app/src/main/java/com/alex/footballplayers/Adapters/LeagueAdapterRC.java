package com.alex.footballplayers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.footballplayers.Activity.PlayersList;
import com.alex.footballplayers.Activity.TeamActivity;
import com.alex.footballplayers.Model.LeagueModel;
import com.alex.footballplayers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueAdapterRC extends RecyclerView.Adapter<LeagueAdapterRC.ViewHolder> {




    private List<LeagueModel> leagueModels;
    private Context context;

    public LeagueAdapterRC(List<LeagueModel> leagueModels, Context context){
        this.leagueModels = leagueModels;
        this.context = context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView league_name;
        public TextView league_country;
        public ImageView league_image;
        public View mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            mainView = itemView;

            league_name     =  itemView.findViewById(R.id.league_name);
            league_country  =  itemView.findViewById(R.id.league_country);
            league_image    =  itemView.findViewById(R.id.league_picture);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.league_cell,parent,false);
        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LeagueModel leagueModel = leagueModels.get(position);

        holder.league_name.setText(leagueModel.getLeague_name());
        holder.league_country.setText(leagueModel.getLeague_country());

        Picasso.with(context)
                .load(leagueModel.getLeague_image_url())
                .into(holder.league_image);


        // on clicklistenner redirection Intent
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teamList = new Intent(context, TeamActivity.class);
                teamList.putExtra("leagueID", leagueModels.get(position).getLeague_id());
                teamList.putExtra("leagueName", leagueModels.get(position).getLeague_name());
                context.startActivity(teamList);
            }
        });




    }

    @Override
    public int getItemCount() {
        return leagueModels.size();
    }
}

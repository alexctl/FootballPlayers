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
import com.alex.footballplayers.Model.PlayerModel;
import com.alex.footballplayers.Model.TeamModel;
import com.alex.footballplayers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerAdapterRC  extends RecyclerView.Adapter<PlayerAdapterRC.ViewHolder>{

    private List<PlayerModel> playerModels;
    private Context context;

    public PlayerAdapterRC(List<PlayerModel> playerModels, Context context){
        this.playerModels = playerModels;
        this.context = context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView player_name;
        public TextView player_dateBirth;
        public TextView player_position;
        public TextView player_amount;
        public ImageView player_picture;
        public View mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            mainView = itemView;

            player_name      = itemView.findViewById(R.id.player_name);
            player_dateBirth = itemView.findViewById(R.id.player_dateBirth);
            player_position  = itemView.findViewById(R.id.player_position);
            player_amount    = itemView.findViewById(R.id.player_amount);
            player_picture   = itemView.findViewById(R.id.player_picture);
        }
    }


    @Override
    public PlayerAdapterRC.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_cell,parent,false);
        return new PlayerAdapterRC.ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PlayerModel playerModel = playerModels.get(position);

        holder.player_name.setText(playerModel.getPlayer_name());
        holder.player_dateBirth.setText("Date birth : "+playerModel.getPlayer_birth());
        holder.player_position.setText("Position : "+playerModel.getPlayer_position());
        holder.player_amount.setText("Ammount Mercato : "+playerModel.getPlayer_amount());

        if(!playerModel.getPlayer_urlPicture_cutout().equals("")
                && !playerModel.getPlayer_urlPicture_cutout().equals("null")
                ){
            Log.e("test image ", playerModel.getPlayer_urlPicture_cutout());
            Picasso.with(context)
                    .load(playerModel.getPlayer_urlPicture_cutout())
                    .into(holder.player_picture);
        }
        else if (!playerModel.getPlayer_urlPicture_thumb().equals("")
                && !playerModel.getPlayer_urlPicture_thumb().equals("null")
                ){
            Picasso.with(context)
                    .load(playerModel.getPlayer_urlPicture_thumb())
                    .into(holder.player_picture);
        }
        else{
            Picasso.with(context)
                    .load(R.drawable.no_image_found)
                    .into(holder.player_picture);
        }
    }

    @Override
    public int getItemCount() {
        return playerModels.size();
    }
}
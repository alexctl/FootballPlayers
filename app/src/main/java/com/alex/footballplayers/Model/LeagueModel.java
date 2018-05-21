package com.alex.footballplayers.Model;

import com.google.gson.annotations.SerializedName;

public class LeagueModel {



    private String league_id;
    private String league_name;
    private String league_image_url;
    private String league_country;


    public String getLeague_id() { return league_id; }
    public String getLeague_name() { return league_name; }
    public String getLeague_country() { return league_country; }
    public String getLeague_image_url() { return league_image_url; }

    public LeagueModel(String league_id, String league_name, String league_country, String league_image_url) {
        this.league_id = league_id;
        this.league_name = league_name;
        this.league_country = league_country;
        this.league_image_url = league_image_url;
    }


}

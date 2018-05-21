package com.alex.footballplayers.Model;

import java.util.List;

public class TeamModel {

    private String team_id;
    private String team_name;
    private String team_image_url;

    public String getTeam_id() { return team_id; }
    public String getTeam_name() { return team_name; }
    public String getTeam_image_url() { return team_image_url; }


    public TeamModel(String team_id, String team_name, String team_image_url) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_image_url = team_image_url;
    }


}
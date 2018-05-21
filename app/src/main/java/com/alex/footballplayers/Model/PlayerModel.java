package com.alex.footballplayers.Model;

public class PlayerModel {

    private String player_name;
    private String player_birth;
    private String player_position;
    private String player_amount;
    private String player_urlPicture_cutout;
    private String player_urlPicture_thumb;

    public String getPlayer_urlPicture_thumb() { return player_urlPicture_thumb; }
    public String getPlayer_name() { return player_name; }
    public String getPlayer_birth() { return player_birth; }
    public String getPlayer_position() { return player_position; }
    public String getPlayer_amount() { return player_amount; }
    public String getPlayer_urlPicture_cutout() { return player_urlPicture_cutout; }

    public PlayerModel(String player_name,
                       String player_birth, String player_position,
                       String player_amount, String player_urlPicture_cutout, String player_urlPicture_thumb) {

        this.player_name = player_name;
        this.player_birth = player_birth;
        this.player_position = player_position;
        this.player_amount = player_amount;
        this.player_urlPicture_cutout = player_urlPicture_cutout;
        this.player_urlPicture_thumb = player_urlPicture_thumb;
    }
}

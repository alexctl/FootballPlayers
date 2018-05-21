package com.alex.footballplayers.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.alex.footballplayers.Adapters.PlayerAdapterRC;
import com.alex.footballplayers.Model.PlayerModel;
import com.alex.footballplayers.R;
import com.alex.footballplayers.Util.Constant;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayersList extends AppCompatActivity {

    private String team_id;
    private String team_name;
    private List<PlayerModel> playersModel;
    private RecyclerView mPlayerRecycler;
    private RecyclerView.Adapter adapter;

    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        team_id = intent.getStringExtra("teamID");
        team_name = intent.getStringExtra("teamName");
        setTitle(team_name);
        mPlayerRecycler = findViewById(R.id.player_recycler);
        mPlayerRecycler.setLayoutManager(new LinearLayoutManager(this));

        mPlayerRecycler.setAdapter(adapter);
        mQueue = Volley.newRequestQueue(this);

        Log.e("test", "test0");
        playerList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void playerList() {
        playersModel = new ArrayList<>();
        Log.e("test", team_id);
        String url = Constant.APIURL+"lookup_all_players.php?id="+team_id;

        Log.e("test", url);
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("player");
                            for (int i=0 ; i< jsonArray.length(); i++){
                                JSONObject players = jsonArray.getJSONObject(i);

                                PlayerModel playerModel= new PlayerModel(
                                        players.getString("strPlayer"),
                                        players.getString("dateBorn"),
                                        players.getString("strPosition"),
                                        players.getString("strSigning"),
                                        players.getString("strCutout"),
                                        players.getString("strThumb")
                                );
                                playersModel.add(playerModel);
                            }
                            adapter = new PlayerAdapterRC(playersModel, getApplicationContext());
                            mPlayerRecycler.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request2);
    }
}

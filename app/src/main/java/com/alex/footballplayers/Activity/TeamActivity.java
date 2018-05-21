package com.alex.footballplayers.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.alex.footballplayers.Adapters.TeamAdapterRC;
import com.alex.footballplayers.Model.TeamModel;
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

public class TeamActivity extends AppCompatActivity {

    private String league_id;
    private String league_name;
    private List<TeamModel> teamsModel;
    private RecyclerView mTeamRecycler;
    private RecyclerView.Adapter adapter;
    private GridLayoutManager layoutManager;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        league_id = intent.getStringExtra("leagueID");
        league_name = intent.getStringExtra("leagueName");
        setTitle(league_name);
        mTeamRecycler = findViewById(R.id.team_recycler);
        layoutManager= new GridLayoutManager(this, 3);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mTeamRecycler.setLayoutManager(layoutManager);

        mQueue = Volley.newRequestQueue(this);

        Log.e("test", "test0");
        teamList();
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

    private void teamList(){

        teamsModel = new ArrayList<>();
        Log.e("test", "test");
        String url = Constant.APIURL+"lookup_all_teams.php?id="+league_id;

        Log.e("test", url);
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");
                            for (int i=0 ; i< jsonArray.length(); i++){
                                JSONObject teams = jsonArray.getJSONObject(i);


                                TeamModel teamModel = new TeamModel(
                                        teams.getString("idTeam"),
                                        teams.getString("strTeam"),
                                        teams.getString("strTeamBadge")
                                );
                                teamsModel.add(teamModel);
                            }
                            adapter = new TeamAdapterRC(teamsModel, getApplicationContext());
                            mTeamRecycler.setAdapter(adapter);
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

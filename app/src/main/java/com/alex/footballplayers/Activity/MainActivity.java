package com.alex.footballplayers.Activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.alex.footballplayers.Adapters.LeagueAdapterRC;
import com.alex.footballplayers.Model.LeagueModel;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLeagueRecycler;
    private RecyclerView.Adapter adapter;
    private List<LeagueModel> leagueModels;
    private RequestQueue mQueue;

    private SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLeagueRecycler = findViewById(R.id.league_recycler);
        mLeagueRecycler.setLayoutManager(new LinearLayoutManager(this));
        //Leagues leagues = new Leagues();
        mLeagueRecycler.setAdapter(adapter);

        leagueModels = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);


        testVolley("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.searchView);

        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint("League...");
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                testVolley(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                testVolley(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void testVolley(final String searchWord) {

        String url = Constant.APIURL+"search_all_leagues.php?s=Soccer";
        leagueModels = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("countrys");
                            for (int i=0 ; i< jsonArray.length(); i++){
                                JSONObject leagues = jsonArray.getJSONObject(i);
                                String nameleagues = leagues.getString("strLeague");
                                //Log.e("Nom league", nameleagues);

                                if (!searchWord.equals("") && nameleagues.toLowerCase().contains(searchWord.toLowerCase())){
                                    LeagueModel leagueModel = new LeagueModel(
                                            leagues.getString("idLeague"),
                                            leagues.getString("strLeague"),
                                            leagues.getString("strCountry"),
                                            leagues.getString("strBadge")
                                    );
                                    leagueModels.add(leagueModel);
                                }else if (searchWord.equals("")){
                                    LeagueModel leagueModel = new LeagueModel(
                                            leagues.getString("idLeague"),
                                            leagues.getString("strLeague"),
                                            leagues.getString("strCountry"),
                                            leagues.getString("strBadge")
                                    );
                                    leagueModels.add(leagueModel);
                                }
                            }
                            adapter = new LeagueAdapterRC(leagueModels, getApplicationContext());
                            mLeagueRecycler.setAdapter(adapter);
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
        mQueue.add(request);
    }
}

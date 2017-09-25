package com.example.stephen.ballislife.services;


import com.example.stephen.ballislife.Constants;
import com.example.stephen.ballislife.models.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsApiService {
    private static  OkHttpClient client = new OkHttpClient();


    public static void searchTeam(String team, Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + Constants.TEAM_STATS_ENDPOINT).newBuilder();
        urlBuilder.addQueryParameter(Constants.TEAM_ID_QUERY_PARAMETER, team);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer" + Constants.ACCESS_TOKEN)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void listTeams(Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + Constants.ALL_TEAMS_ENDPOINT).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void listLeaders(String category, Callback callback) {


        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + Constants.ALL_LEADERS_ENDPOINT).newBuilder();
        urlBuilder.addQueryParameter(Constants.CATEGORY_ID_QUERY_PARAMETER, category);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer" + Constants.ACCESS_TOKEN)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Team> processResults(Response response) {
        ArrayList<Team> teams = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
            JSONObject newsJSON = new JSONObject(jsonData);
            JSONArray generalNewsJSON = newsJSON.getJSONArray("team_stats");
            for (int i = 0; i < generalNewsJSON.length(); i++) {
                JSONObject anyNewsJSON = generalNewsJSON.getJSONObject(i).getJSONObject("team");
                String name = anyNewsJSON.getString("full_name");
                String abbreviation = anyNewsJSON.getString("abbreviation");
                String conference = anyNewsJSON.getString("conference");
                String division = anyNewsJSON.getString("division");
                String state = anyNewsJSON.getString("state");


                Team team = new Team(name, abbreviation, conference, division, state);
                teams.add(team);

            }

        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return teams;
    }


}

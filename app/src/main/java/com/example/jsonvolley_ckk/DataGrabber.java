package com.example.jsonvolley_ckk;

import android.content.Context;
import android.provider.MediaStore;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.jsonvolley_ckk.dummy.DummyContent.ITEM_MAP;

public class DataGrabber {

    private RequestQueue mQueue;

    public static Map<String, VideoGame> ITEM_MAP = null;

    public static List<VideoGame> GAMES = null;

    public void getThatDataFromThatURL(Context context){
        if(GAMES != null){
            return;
        }

        mQueue = Volley.newRequestQueue(context);

        parseJason(context);

        GAMES = new ArrayList<>();
        ITEM_MAP = new HashMap<>();
    }

    private void parseJason(final Context context){
        String url = context.getString(R.string.videogame_url);

        final Gson gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("videoGames");

                            if(jsonArray.length() > 0) {
                                List<VideoGame> games = Arrays.asList(gson.fromJson(jsonArray.toString(), VideoGame[].class));

                                for(VideoGame game: games){
                                    addObjectToList(game);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void addObjectToList (VideoGame  theGame){
        GAMES.add(theGame);
        ITEM_MAP.put(theGame.videoGameName, theGame);
    }
}

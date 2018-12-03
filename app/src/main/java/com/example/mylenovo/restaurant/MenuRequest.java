package com.example.mylenovo.restaurant;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private Callback activity;
    private ArrayList<MenuItem> menuArrayList;

    // Method in interface are called, after current methods are done
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    // Constructor
    public MenuRequest(Context inputContext) {
        this.context = inputContext;
    }

    // Get Items from url. Using interface Callback
    public void getMenu(Callback activity, String category) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url +
                "menu?category=" + category, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // No items found
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // When items are retrieved succesful, put them as MenuItem-object in a arraylist
        try {
            menuArrayList = new ArrayList<>();
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                MenuItem menuItem = new MenuItem();
                JSONObject itemsJSON = items.getJSONObject(i);

                menuItem.setName(itemsJSON.getString("name"));
                menuItem.setDescription(itemsJSON.getString("description"));
                menuItem.setCategory(itemsJSON.getString("category"));
                menuItem.setPrice(itemsJSON.getInt("price"));
                menuItem.setImageUrl(itemsJSON.getString("image_url"));

                menuArrayList.add(menuItem);
            }
            // Call gotMenu in MenuActivity
            activity.gotMenu(menuArrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


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

public class CategoriesRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    private Context context;
    private Callback activity;
    private ArrayList<String> arrayList;

    // Method in interface are called, after current methods are done
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor
    public CategoriesRequest(Context inputContext) {
        this.context = inputContext;
    }

    // Get categories from url. Using interface Callback
    void getCategories(Callback inputActivity) {
        this.activity = inputActivity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url +
                "categories", null, this, this);
        queue.add(jsonObjectRequest);
    }


    @Override
    public void onResponse(JSONObject response) {
        // When categories are retrieved succesful, put them in a arraylist
        arrayList = new ArrayList<>();
        JSONArray categories = null;
        try {
            categories = response.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                arrayList.add(categories.getString(i));
                System.out.println(categories.getString(i));
            }
            // Call gotCategories in CategorieActivity
            activity.gotCategories(arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        // No categries found
        activity.gotCategoriesError(error.getMessage());
    }
}

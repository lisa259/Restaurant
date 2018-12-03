package com.example.mylenovo.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity  implements CategoriesRequest.Callback{

    private ListView lv_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Create new CategoriesRequests object
        CategoriesRequest request = new CategoriesRequest(this);
        // Call getCategories method
        request.getCategories(this);

        // Set onClickListener to listview
        onListItemClick listener = new onListItemClick();
        lv_categories = findViewById(R.id.lv_categories);
        lv_categories.setOnItemClickListener(listener);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        // Categories are recieved, save in adapter to show in listview
        lv_categories = findViewById(R.id.lv_categories);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_categorie_item, categories);
        lv_categories.setAdapter(arrayAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        // No categories found
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class onListItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // When clicked on category in listview, go to MenuActivity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            String category = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}

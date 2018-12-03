package com.example.mylenovo.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements  MenuRequest.Callback {

    ListView lv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get chosen category
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Create new MenuRequest object and call getMenu method
        MenuRequest menuRequest = new MenuRequest(this);
        menuRequest.getMenu(this, category);

        // Set onClickListener to listview
        lv_menu = findViewById(R.id.lv_menu);
        onListItemClick listener = new onListItemClick();
        lv_menu.setOnItemClickListener(listener);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        // Items are recieved, save in MenuAdapter to show in listview
        lv_menu = findViewById(R.id.lv_menu);
        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        lv_menu.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        // No items found
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class onListItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // When clicked on dish in listview, go to MenuItemActivity
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("menuItem", menuItem);
            startActivity(intent);
        }
    }
}

package com.example.mylenovo.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get dish to show all details
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("menuItem");

        ImageView image = findViewById(R.id.iv_item);

        TextView category = findViewById(R.id.tv_category);
        TextView name = findViewById(R.id.tv_name);
        TextView price = findViewById(R.id.tv_price);
        TextView description = findViewById(R.id.tv_description);

        // Set text en image
        category.setText(menuItem.getCategory());
        name.setText(menuItem.getName());
        price.setText("€ " + menuItem.getPrice());
        description.setText(menuItem.getDescription());

        Picasso.get().load(menuItem.getImageUrl()).into(image);

    }
}

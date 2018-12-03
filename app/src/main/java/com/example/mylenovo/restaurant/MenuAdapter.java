package com.example.mylenovo.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> menuItems;

    // Constructor
    public MenuAdapter(Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
        menuItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // creates a view (menu_item) to show in listView
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // Get textView and imageView
        TextView name = convertView.findViewById(R.id.tv_menu_name);
        ImageView image = convertView.findViewById(R.id.iv_menu);
        TextView price = convertView.findViewById(R.id.tv_menu_price);

        // Set text
        name.setText(menuItems.get(position).getName());
        price.setText("€ " + menuItems.get(position).getPrice());

        // Get Image from ImageUrl and set to imageview
        Picasso.get().load(menuItems.get(position).getImageUrl()).into(image);

        return convertView;
    }
}

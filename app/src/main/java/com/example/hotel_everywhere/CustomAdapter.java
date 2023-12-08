package com.example.hotel_everywhere;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ListItem> {
    private final Context context;
    private final List<ListItem> data;

    public CustomAdapter(Context context, List<ListItem> data) {
        super(context, R.layout. food_list, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.food_list, parent, false);
        }

        ImageView imageView = rowView.findViewById(R.id.imageView);
        TextView textView = rowView.findViewById(R.id.textView);

        ListItem listItem = data.get(position);
        textView.setText(listItem.getItemName());

        imageView.setImageResource(listItem.getImageResourceId());

        return rowView;
    }
}

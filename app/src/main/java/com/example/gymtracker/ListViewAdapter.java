package com.example.gymtracker;

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

public class ListViewAdapter extends ArrayAdapter<String> {
    List<String> names;
    List<Integer> pictures;
    Context context;

    public ListViewAdapter(@NonNull Context context, List<String> names, List<Integer> pictures) {
        super(context, R.layout.listview_item);
        this.names=names;
        this.pictures=pictures;
        this.context=context;
    }

    @Override
    public int getCount() {
        return names.toArray().length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.listview_item, parent, false);
            viewHolder.image = convertView.findViewById(R.id.imageView);
            viewHolder.text = convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.image.setImageResource(pictures.get(position));
        viewHolder.text.setText(names.get(position));

        return convertView;
    }

    static class ViewHolder{
        ImageView image;
        TextView text;
    }
}

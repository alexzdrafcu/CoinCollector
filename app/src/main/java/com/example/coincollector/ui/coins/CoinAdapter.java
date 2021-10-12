package com.example.coincollector.ui.coins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.coincollector.Coin;
import com.example.coincollector.R;


import java.util.ArrayList;

public class CoinAdapter extends ArrayAdapter<Coin> {

    private Context mContext;
    int mResource;

    public CoinAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Coin> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getCoinId();
        String title = getItem(position).getCoinTitle();
        String material = getItem(position).getCoinMaterial();
        String diameter = getItem(position).getCoinDiameter();
        String weight = getItem(position).getCoinWeight();
        String edge = getItem(position).getCoinEdge();
        String imgUrl = getItem(position).getCoinImageUrl();

        Coin coin = new Coin (id,title,material,diameter,weight,edge,imgUrl);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView cName = convertView.findViewById(R.id.title_coin_textview);
        TextView cMaterial = convertView.findViewById(R.id.material_coin_textview);
        TextView cDiameter = convertView.findViewById(R.id.diameter_coin_textview);
        TextView cWeight = convertView.findViewById(R.id.weight_coin_textview);
        TextView cEdge = convertView.findViewById(R.id.edge_coin_textview);
        ImageView cImage = convertView.findViewById(R.id.coin_imageview);

        cName.setText(title);
        cMaterial.setText(material);
        cDiameter.setText(diameter);
        cWeight.setText(weight);
        cEdge.setText(edge);
        Glide.with(getContext()).load(imgUrl).into(cImage);
        return convertView;
    }
}


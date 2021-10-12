package com.example.coincollector.ui.trending;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.coincollector.R;
import com.example.coincollector.TopCoin;

import java.util.ArrayList;

public class TopCoinAdapter extends ArrayAdapter<TopCoin> {
    private Context mContext;
    int mResource;

    public TopCoinAdapter(@NonNull Context context,int resource, @NonNull ArrayList<TopCoin> topCoin) {
        super(context,resource,topCoin);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        final TopCoin topCoin = getItem(position);

        TextView numeTextView = (TextView) convertView.findViewById(R.id.title_top_coins_textview);
        numeTextView.setText(topCoin.getTopCoinNume());
        TextView yearTextView = (TextView) convertView.findViewById(R.id.year_top_coins_textview);
        yearTextView.setText(topCoin.getTopCoinAn());
        TextView materialTextView = (TextView) convertView.findViewById(R.id.material_top_coins_textview);
        materialTextView.setText(topCoin.getTopCoinMaterial());
        TextView pretTextView = (TextView) convertView.findViewById(R.id.pret_top_coins_textview);
        pretTextView.setText(topCoin.getTopCoinPret());
        ImageView topCoinImageView = (ImageView) convertView.findViewById(R.id.top_coins_imageview);
        Glide.with(getContext()).load(topCoin.getTopCoinImageURL()).into(topCoinImageView);

        Button detailsBtn = (Button)convertView.findViewById(R.id.search_details_button);
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                DetailedTopCoin fragment = new DetailedTopCoin();
                FragmentTransaction transaction;

                Bundle topCoinBundle = new Bundle();
                topCoinBundle.putString("nume",topCoin.getTopCoinNume());
                topCoinBundle.putString("an",topCoin.getTopCoinAn());
                topCoinBundle.putString("valoareNominala",topCoin.getTopCoinValoareNominala());
                topCoinBundle.putString("material",topCoin.getTopCoinMaterial());
                topCoinBundle.putString("greutate",topCoin.getTopCoinGreutate());
                topCoinBundle.putString("diametru",topCoin.getTopCoinDiametru());
                topCoinBundle.putString("margine",topCoin.getTopCoinMargine());
                topCoinBundle.putString("detalii1",topCoin.getTopCoinDetalii1());
                topCoinBundle.putString("detalii2",topCoin.getTopCoinDetalii2());
                topCoinBundle.putString("detalii3",topCoin.getTopCoinDetalii3());
                topCoinBundle.putString("pret",topCoin.getTopCoinPret());
                topCoinBundle.putString("imgurl",topCoin.getTopCoinImageURL());
                fragment.setArguments(topCoinBundle);
                transaction = ((Activity)mContext).getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,fragment,"tag");
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        return convertView;
    }
}

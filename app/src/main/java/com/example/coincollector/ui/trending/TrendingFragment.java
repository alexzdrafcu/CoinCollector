package com.example.coincollector.ui.trending;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.coincollector.R;
import com.example.coincollector.TopCoin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TrendingFragment extends Fragment {

    TextView dateTextView;
    public ArrayList<TopCoin> topCoins;
    public ArrayList<TopCoin> jsonData = new ArrayList<>();
    ListView mListView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_trending, container, false);


        dateTextView = root.findViewById(R.id.search_time_textView);

        content();

        topCoins = new ArrayList<>();
        mListView = root.findViewById(R.id.search_list);

        TopCoinAdapter adapter = new TopCoinAdapter(root.getContext(), R.layout.top_coins_layout, topCoins);
        mListView.setAdapter(adapter);
        new FetchContent().execute();

        return root;
    }

    public void content() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        dateTextView.setText(dateTime);
        refresh(5000);
    }

    private void refresh(int milliseconds){
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
                new FetchContent().execute();
            }
        };
        handler.postDelayed(runnable,milliseconds);
    }


    public void doUpdate() {
        if(getActivity() == null || topCoins == null)
            return;
        if(jsonData != null){
            topCoins =new ArrayList<TopCoin>(jsonData);
        }

    }


    private class FetchContent extends AsyncTask<Void,Void,ArrayList<TopCoin>> {
        TopCoinAdapter adapter;
        @Override
        protected void onPreExecute() {
            adapter = (TopCoinAdapter)mListView.getAdapter();
            adapter.clear();
        }

        @Override
        protected ArrayList<TopCoin> doInBackground(Void... params) {
            ArrayList<TopCoin> topc = new ArrayList<TopCoin>();
            try {
                return new Fetcher().extractTopCoin();
            } catch (Exception e) {
                e.printStackTrace();
            }
           return null;
        }

        @Override
        protected void onPostExecute(ArrayList<TopCoin> result){
            jsonData =new ArrayList<TopCoin>(result);
            for(TopCoin coin: result)
            {
                adapter.add(coin);
            }
            doUpdate();
        }
    }
}






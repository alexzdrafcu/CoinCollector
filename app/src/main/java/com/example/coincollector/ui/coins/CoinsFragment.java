package com.example.coincollector.ui.coins;



import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.coincollector.Coin;
import com.example.coincollector.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class CoinsFragment extends Fragment {

    private ArrayList<Coin> coinsList;
    CoinsDatabase coinsDatabase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_coins, container, false);


        final ListView mListView = root.findViewById(R.id.coin_list);

        coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
        List<DBDetails> listaDetalii = coinsDatabase.detailsDao().getAll();
        List<DBCoin> listaMonede = coinsDatabase.coinDao().getAll();


        coinsList = new ArrayList<>();
        populateCoinList(listaMonede,listaDetalii);


        final CoinAdapter adapter = new CoinAdapter(root.getContext(), R.layout.coin_list_layout, coinsList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoinPageFragment newFragment = new CoinPageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id",adapter.getItem(position).getCoinId());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.commit();
            }

        });

        FloatingActionButton btn = (FloatingActionButton) root.findViewById(R.id.coins_floating_add_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    AddToDatabaseFragment newFragment = new AddToDatabaseFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        Button resetButton = (Button) root.findViewById(R.id.coins_resetdb_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                List<DBDetails> listaDetalii = coinsDatabase.detailsDao().getAll();
                coinsDatabase.detailsDao().reset(listaDetalii);
                List<DBCoin> listaMonede = coinsDatabase.coinDao().getAll();
                coinsDatabase.coinDao().reset(listaMonede);

                Resources res = getResources();
                String[] allCoins = res.getStringArray(R.array.coins);
                String[] allMaterials = res.getStringArray(R.array.material);
                String[] allDameters = res.getStringArray(R.array.diameter);
                String[] allWeights = res.getStringArray(R.array.weight);
                String[] allEdges = res.getStringArray(R.array.edge);
                String[] allImgurls = res.getStringArray(R.array.imgurls);

                for(int i = 0 ;i < 4; i++){
                    DBCoin coinObj = new DBCoin();
                    DBDetails detailsObj = new DBDetails();
                    coinObj.setCoin(allCoins[i]);
                    coinObj.setFk_detailsId(detailsObj.getDetailsId());
                    detailsObj.setMaterial(allMaterials[i]);
                    detailsObj.setDiametru(allDameters[i]);
                    detailsObj.setGreutate(allWeights[i]);
                    detailsObj.setMuchie(allEdges[i]);
                    detailsObj.setImgUrl(allImgurls[i]);
                    coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                    coinsDatabase.detailsDao().insert(detailsObj);
                    coinsDatabase.coinDao().insert(coinObj);
                }

                CoinsFragment newFragment = new CoinsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }

        public void populateCoinList(List<DBCoin> listaMonede,List<DBDetails> listaDetalii){
            for(int i=0; i < listaMonede.size();i++){
                Coin coin = new Coin();
                coin.setCoinId(listaMonede.get(i).getCoinId());
                coin.setCoinTitle(listaMonede.get(i).getCoin());
                coin.setCoinDiameter(listaDetalii.get(i).getDiametru());
                coin.setCoinEdge(listaDetalii.get(i).getMuchie());
                coin.setCoinMaterial(listaDetalii.get(i).getMaterial());
                coin.setCoinWeight(listaDetalii.get(i).getGreutate());
                coin.setCoinImageUrl(listaDetalii.get(i).getImgUrl());
                coinsList.add(coin);
            }
    }
}


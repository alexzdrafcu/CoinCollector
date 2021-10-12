package com.example.coincollector.ui.coins;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.coincollector.R;

import java.util.List;

public class AddToDatabaseFragment extends Fragment {

    CoinsDatabase coinsDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_addtodb, container, false);

        final SharedPreferences preferedCoin = getContext().getSharedPreferences("MyPreferedCoin", Context.MODE_PRIVATE);

        final EditText numeEditText = (EditText) root.findViewById(R.id.adddb_add_title_edittext);
        final EditText materialEditText = (EditText) root.findViewById(R.id.adddb_add_material_edittext);
        final EditText diametruEditText = (EditText) root.findViewById(R.id.adddb_add_diameter_edittext);
        final EditText greutateEditText = (EditText) root.findViewById(R.id.adddb_add_weight_edittext);
        final EditText muchieEditText = (EditText) root.findViewById(R.id.adddb_add_edge_edittext);
        final EditText imgurlEditText = (EditText) root.findViewById(R.id.adddb_add_imgurl_edittext);


        final DBDetails detailsObj = new DBDetails();
        final DBCoin coinObj = new DBCoin();


        Button addButton = (Button) root.findViewById(R.id.adddb_add_db_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nume = numeEditText.getText().toString();
                final String material = materialEditText.getText().toString();
                final String diametru = diametruEditText.getText().toString();
                final String greutate = greutateEditText.getText().toString();
                final String muchie = muchieEditText.getText().toString();
                final String imgurl = imgurlEditText.getText().toString();

                {
                    if (nume.isEmpty()){
                        numeEditText.setError("Empty field!");
                        numeEditText.requestFocus();
                    }
                    else if (material.isEmpty()){
                        materialEditText.setError("Empty field!");
                        materialEditText.requestFocus();
                    }
                    else if (diametru.isEmpty()){
                        diametruEditText.setError("Empty field!");
                        diametruEditText.requestFocus();
                    }
                    else if (greutate.isEmpty()){
                        greutateEditText.setError("Empty field!");
                        greutateEditText.requestFocus();
                    }
                    else if (muchie.isEmpty()){
                        muchieEditText.setError("Empty field!");
                        muchieEditText.requestFocus();
                    }
                    else if (imgurl.isEmpty()){
                        muchieEditText.setError("Empty field!");
                        muchieEditText.requestFocus();
                    }
                    else {
                        coinObj.setCoin(nume);
                        coinObj.setFk_detailsId(detailsObj.getDetailsId());
                        detailsObj.setMaterial(material);
                        detailsObj.setDiametru(diametru);
                        detailsObj.setGreutate(greutate);
                        detailsObj.setMuchie(muchie);
                        detailsObj.setImgUrl(imgurl);
                        coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                        coinsDatabase.detailsDao().insert(detailsObj);
                        coinsDatabase.coinDao().insert(coinObj);

                        CoinsFragment newFragment = new CoinsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                        transaction.commit();
                        Toast.makeText(getContext(),"Coin added",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        Button resetButton = (Button) root.findViewById(R.id.adddb_reset_db_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                List<DBDetails> listaDetalii = coinsDatabase.detailsDao().getAll();
                coinsDatabase.detailsDao().reset(listaDetalii);
                List<DBCoin> listaMonede = coinsDatabase.coinDao().getAll();
                coinsDatabase.coinDao().reset(listaMonede);
            }
        });


        Button backButton = (Button) root.findViewById((R.id.adddb_cancel_button));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinsFragment newFragment = new CoinsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.commit();
            }
        });

        Button preferenceButton = (Button) root.findViewById(R.id.adddb_preference_button);
        preferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferedCoin !=null)
                {
                    String moneda = preferedCoin.getString("moneda","");
                    String material = preferedCoin.getString("material","");
                    String diametru = preferedCoin.getString("diametru","");
                    String greutate = preferedCoin.getString("greutate","");
                    String muchie = preferedCoin.getString("muchie","");
                    String imgurl = preferedCoin.getString("imgurl","");

                    numeEditText.setText(moneda);
                    materialEditText.setText(material);
                    diametruEditText.setText(diametru);
                    greutateEditText.setText(greutate);
                    muchieEditText.setText(muchie);
                    imgurlEditText.setText(imgurl);
                }
            }
        });

        return root;
    }
}

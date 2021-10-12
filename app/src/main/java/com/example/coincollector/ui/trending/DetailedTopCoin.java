package com.example.coincollector.ui.trending;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.coincollector.R;


public class DetailedTopCoin extends Fragment {

    SharedPreferences preferenceCoin;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_detalied_coin, container, false);

        //PREFERENCE
        preferenceCoin = getActivity().getSharedPreferences("MyPreferedCoin", Context.MODE_PRIVATE);

        Bundle topCoinBundle = this.getArguments();
        final String nume = topCoinBundle.getString("nume");
        String an = topCoinBundle.getString("an");
        String valoareNominala = topCoinBundle.getString("valoareNominala");
        final String material = topCoinBundle.getString("material");
        final String greutate = topCoinBundle.getString("greutate");
        final String diametru = topCoinBundle.getString("diametru");
        final String margine = topCoinBundle.getString("margine");
        String detalii1 = topCoinBundle.getString("detalii1");
        String detalii2 = topCoinBundle.getString("detalii2");
        String detalii3 = topCoinBundle.getString("detalii3");
        String pret = topCoinBundle.getString("pret");
        final String imgurl = topCoinBundle.getString("imgurl");

        final ImageView imageView =  root.findViewById(R.id.detailed_coin_imageview);
        final TextView numeTextView = root.findViewById(R.id.detailed_title_textview);
        final TextView anTextView = root.findViewById(R.id.detailed_an_textview);
        final TextView valoareNominalaTextView = root.findViewById(R.id.detailed_valoareNominala_textview);
        final TextView materialTextView = root.findViewById(R.id.detailed_material_textview);
        final TextView greutateTextView = root.findViewById(R.id.detailed_greutate_textview);
        final TextView diametruTextView = root.findViewById(R.id.detailed_diametru_textview);
        final TextView margineTextView = root.findViewById(R.id.detailed_margine_textview);
        final TextView detalii1TextView = root.findViewById(R.id.detailed_detalii1_textview);
        final TextView detalii2TextView = root.findViewById(R.id.detailed_detalii2_textview);
        final TextView detalii3TextView = root.findViewById(R.id.detailed_detalii3_textview);
        final TextView pretTextView = root.findViewById(R.id.detailed_pret_textview);
        numeTextView.setText(nume);
        anTextView.setText(an);
        valoareNominalaTextView.setText(valoareNominala);
        materialTextView.setText(material);
        greutateTextView.setText(greutate);
        diametruTextView.setText(diametru);
        margineTextView.setText(margine);
        detalii1TextView.setText(detalii1);
        detalii2TextView.setText(detalii2);
        detalii3TextView.setText(detalii3);
        pretTextView.setText(pret);
        Glide.with(root.getContext()).load(imgurl).into(imageView);

        Button buttonBack = root.findViewById(R.id.detailed_cancel_button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    getActivity().getFragmentManager().popBackStack();
                }
            }
        });

        Button addPref = root.findViewById(R.id.detailed_preference_button);
        addPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor prefEditor = preferenceCoin.edit();
                prefEditor.putString("moneda", nume);
                prefEditor.putString("material", material);
                prefEditor.putString("diametru", diametru);
                prefEditor.putString("greutate", greutate);
                prefEditor.putString("muchie", margine);
                prefEditor.putString("imgurl", imgurl);
                prefEditor.apply();
                Toast.makeText(root.getContext(),"Coin saved!",Toast.LENGTH_LONG).show();
            }
        });



        return root;
    }


}
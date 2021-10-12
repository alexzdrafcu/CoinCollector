package com.example.coincollector.ui.coins;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.coincollector.Coin;
import com.example.coincollector.R;
import com.example.coincollector.RegisterActivity;
import com.example.coincollector.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CoinPageFragment extends Fragment {

    CoinsDatabase coinsDatabase;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       final View root = inflater.inflate(R.layout.fragment_coin_page, container, false);

        Bundle topCoinBundle = this.getArguments();
        final int coinId = topCoinBundle.getInt("id");

        final TextView numeTextview = (TextView) root.findViewById(R.id.each_title_coin_textview);
        final ImageView coinImage = (ImageView) root.findViewById(R.id.each_coin_imageview);
        final EditText materialEditText = (EditText) root.findViewById(R.id.each_material_edittext);
        final EditText diametruEditText = (EditText) root.findViewById(R.id.each_diameter_edittext);
        final EditText greutateEditText = (EditText) root.findViewById(R.id.each_weight_edittext);
        final EditText muchieEditText = (EditText) root.findViewById(R.id.each_edge_edittext);

        coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
        final DBDetails detailsToShow = coinsDatabase.detailsDao().getDetailsById(coinId);
        final DBCoin coinToShow = coinsDatabase.coinDao().getCoinsById(coinId);
        Glide.with(getContext()).load(detailsToShow.getImgUrl()).into(coinImage);
        numeTextview.setText(coinToShow.getCoin());
        materialEditText.setText(detailsToShow.getMaterial());
        diametruEditText.setText(detailsToShow.getDiametru());
        greutateEditText.setText(detailsToShow.getGreutate());
        muchieEditText.setText(detailsToShow.getMuchie());



        Button deleteButton = (Button) root.findViewById(R.id.each_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                DBDetails detailsDelete = coinsDatabase.detailsDao().getDetailsById(coinId);
                DBCoin coinDelete = coinsDatabase.coinDao().getCoinsById(coinId);
                coinsDatabase.detailsDao().delete(detailsDelete);
                coinsDatabase.coinDao().delete(coinDelete);

                CoinsFragment newFragment = new CoinsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.commit();
                Toast.makeText(getContext(),"Coin deleted",Toast.LENGTH_LONG).show();

            }
        });

        Button updateButton = (Button) root.findViewById(R.id.each_update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String material = materialEditText.getText().toString();
                final String diametru = diametruEditText.getText().toString();
                final String greutate = greutateEditText.getText().toString();
                final String muchie = muchieEditText.getText().toString();

                if (material.isEmpty()){
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
                else {

                    coinsDatabase = CoinsDatabase.getInstance(getActivity().getApplicationContext());
                    coinsDatabase.detailsDao().updateById(coinId,material,diametru,greutate,muchie);
                    Toast.makeText(getContext(),"Coin updated",Toast.LENGTH_LONG).show();
                    CoinsFragment newFragment = new CoinsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                    transaction.commit();
                }


            }

        });

        Button addToWalletButton = (Button) root.findViewById((R.id.each_add_to_wallet_button));
        addToWalletButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final User newUser = new User();
                final FirebaseUser oldUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(oldUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        newUser.nume = user.nume;
                        newUser.email = user.email;
                        Coin coidToAdd = new Coin(coinToShow.getCoin(),detailsToShow.getMaterial(),detailsToShow.getDiametru(),detailsToShow.getGreutate(),detailsToShow.getMuchie(),detailsToShow.getImgUrl());
                        newUser.coins = new ArrayList<>();
                        if(user.coins != null) {
                            for (Coin c : user.coins) {
                                newUser.coins.add(c);
                            }
                        }
                        newUser.coins.add(coidToAdd);
                        FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Coin added!", Toast.LENGTH_LONG).show();
                        }

                        else{
                                Toast.makeText(getContext(), "Error!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(root.getContext(),"Error!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button backButton = (Button) root.findViewById((R.id.each_back_button));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinsFragment newFragment = new CoinsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.commit();
            }
        });
        return root;
    }

 
}
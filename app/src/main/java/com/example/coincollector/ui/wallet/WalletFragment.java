package com.example.coincollector.ui.wallet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.coincollector.Coin;
import com.example.coincollector.R;
import com.example.coincollector.User;
import com.example.coincollector.ui.coins.CoinAdapter;
import com.example.coincollector.ui.coins.CoinsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class WalletFragment extends Fragment {

    public ArrayList<Coin> walletList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_wallet, container, false);

        final ListView mListView = root.findViewById(R.id.wallet_list);
        walletList = new ArrayList<>();

        AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                populateWalletList(walletList);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                final ImageView img = root.findViewById(R.id.wallet_img);
                final TextView text = root.findViewById(R.id.wallet_text);
                if(walletList.size()>0) {
                    img.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                }
                final CoinAdapter adapter = new CoinAdapter(root.getContext(), R.layout.coin_list_layout, walletList);
                mListView.setAdapter(adapter);

                mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        walletList.remove(position);
                        adapter.notifyDataSetChanged();
                        refreshUserOnDelete(walletList);
                        if(walletList.size()==0){
                            img.setVisibility(View.VISIBLE);
                            text.setVisibility(View.VISIBLE);
                        }
                        return false;
                    }
                });
            }
        };
        task.execute();

        FloatingActionButton goToCoins = root.findViewById(R.id.wallet_fab);
        goToCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinsFragment newFragment = new CoinsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,newFragment,"tag");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }


    public void populateWalletList(final ArrayList<Coin> list){
        final FirebaseUser oldUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(oldUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if(user.coins != null) {
                    for (Coin c : user.coins) {
                        list.add(c);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void refreshUserOnDelete(final ArrayList<Coin> walletList){
        final User newUser = new User();
        final FirebaseUser oldUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(oldUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                newUser.nume = user.nume;
                newUser.email = user.email;
                newUser.coins = new ArrayList<>();
                if(user.coins != null) {
                    for (Coin c : walletList) {
                        newUser.coins.add(c);
                    }
                }
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(newUser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
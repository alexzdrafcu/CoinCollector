package com.example.coincollector.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.coincollector.LoginActivity;
import com.example.coincollector.R;
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


public class ProfileFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logOutButton;
    private Button changePassButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        logOutButton = (Button) root.findViewById(R.id.whishlist_logOut_button);
        changePassButton = (Button) root.findViewById(R.id.whishlist_change_button);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();

                }
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView numeTextView = (TextView) root.findViewById(R.id.whishlist_numeprenume_textview);
        final TextView emailTextView = (TextView) root.findViewById(R.id.whishlist_email_textview);
        final EditText newPassEditText = (EditText) root.findViewById(R.id.whishlist_password_editText);
        final EditText reNewPassEditText = (EditText) root.findViewById(R.id.whishlist_repassword_editText);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if(user != null){
                    String nume = user.nume;
                    String email = user.email;
                    numeTextView.setText(nume);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(root.getContext(),"Error!",Toast.LENGTH_LONG).show();
            }
        });

        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    final String newPass = newPassEditText.getText().toString();
                    final String reNewPass = reNewPassEditText.getText().toString();
                    if(!newPass.isEmpty())
                        if(!(newPass.length() < 6))
                            if(newPass.equals(reNewPass)){
                                    user.updatePassword(newPass.trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(root.getContext(),"Password updated!",Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(root.getContext(),"Password update error!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                reNewPassEditText.setError("Passwords don't match!");
                                reNewPassEditText.requestFocus();
                            }
                        else{
                            newPassEditText.setError("Password too short!");
                            newPassEditText.requestFocus();
                        }
                    else{
                        newPassEditText.setError("Empty password!");
                        newPassEditText.requestFocus();
                  }


                }
            }
        });

        return root;
    }


}

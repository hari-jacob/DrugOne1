package com.example.drugone1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class FragmentAccount extends Fragment {

    View v;
    private FirebaseAuth mAuth;
    private EditText loginMail,loginPass,regMail,regPass,regAddress;
    private Button btnLogin;
    private Button regBtn;
    private Intent myIntent;


    private void CreateUserAccount(String email, String address, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // user account created successfully
                            showMessage("Account created");
                            updateUI();
                            // after we created user account we need to update his profile picture and name




                        }
                        else
                        {
                            // account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());

                        }
                    }
                });

    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    showMessage("Sign in success");
                    updateUI();

                }
                else {
                    showMessage(task.getException().getMessage());
                }


            }
        });

    }

    private void showMessage(String message) {

        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }



    public FragmentAccount() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v= inflater.inflate(R.layout.account_fragment,container,false);
        FirebaseApp.initializeApp(getActivity());
        regMail = v.findViewById(R.id.acc1e3);
        regAddress = v.findViewById(R.id.acc1e4);
        regPass = v.findViewById(R.id.acc1e5);
        regBtn = v.findViewById(R.id.acc1b2);
        loginMail  =v.findViewById(R.id.acc1e1);
        loginPass = v.findViewById(R.id.acc1e2);
        btnLogin = v.findViewById(R.id.acc1b1);
        myIntent = new Intent(getActivity(), com.example.drugone1.MainActivity.class);

        mAuth= FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail = loginMail.getText().toString();
                final String password = loginPass.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    Toast toast=Toast.makeText(getActivity(),"Please validate all fields",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else
                {
//                    Toast.makeText(getActivity(),"Log In Successful",Toast.LENGTH_LONG).show();
                    signIn(mail,password);
                }
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = regMail.getText().toString();
                final String password = regPass.getText().toString();
                final String address = regAddress.getText().toString();

                if( email.isEmpty() ||  address.isEmpty() || password.isEmpty()) {
                    // something goes wrong : all fields must be filled
                    // we need to display an error message
                    showMessage("Please Verify all fields") ;
                }
                else {
                    // everything is ok and all fields are filled now we can start creating user account
                    // CreateUserAccount method will try to create the user if the email is valid
                    showMessage("Creating");
                    CreateUserAccount(email,address,password);
                }
            }

//            private void showMessage(String message) {
//                Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
//
//            }
        });
        return v;
    }
    private void updateUI() {
        startActivity(myIntent);
        getActivity().finish();
    }
}
package com.bima.maknews.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bima.maknews.R;
import com.bima.maknews.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrasiActivity extends AppCompatActivity {
    private static final String TAG = "RegistrasiActivity";
    User user;
    DatabaseReference reff;
    long valueid = 0;

    private ProgressDialog loading;
    private FirebaseAuth mAuth;
    EditText mEmail,mPassword,mPasswordcon,mNama;
    TextView tidaksama,kurang6,erroremail;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mAuth = FirebaseAuth.getInstance();
        mNama= (EditText)findViewById(R.id.edtNama);
        mEmail = (EditText)findViewById(R.id.edtEmail);
        mPassword = (EditText)findViewById(R.id.edtPassword);
        mPasswordcon = (EditText)findViewById(R.id.edtKonfirPassword);
        tidaksama = (TextView) findViewById(R.id.tvErrorSandi);
        kurang6 = (TextView) findViewById(R.id.kurangdari6);
        erroremail = (TextView) findViewById(R.id.emailsalah);

        loading = new ProgressDialog(this);
        loading.setTitle("Mohon tunggu");
        loading.setCancelable(false);
        loading.setMessage("Sedang memproses data");
        loading.dismiss();
        kurang6.setVisibility(View.GONE);
        tidaksama.setVisibility(View.GONE);
        erroremail.setVisibility(View.GONE);

        user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("user");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    valueid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void btnDaftar(View view) {

        final String nama = mNama.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String passwordcon = mPasswordcon.getText().toString().trim();
//        Log.d(TAG,""+password.length()+"");
        if (email.contains("@")) {

            if(password.equals(passwordcon)){
                if (passwordcon.length()<6){
                    kurang6.setVisibility(View.VISIBLE);
                    tidaksama.setVisibility(View.GONE);
                    erroremail.setVisibility(View.GONE);
                }else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegistrasiActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                    user.setNama(nama);
                    user.setEmail(email);
                    user.setPassword(password);
                    String idparse = Long.toString(valueid+1);
                    user.setId(idparse);
                    reff.child(String.valueOf(valueid+1)).setValue(user);
                    loading.show();
                    Intent s = new Intent(this,LoginActivity.class);
                    startActivity(s);
                }
            }else{
                tidaksama.setVisibility(View.VISIBLE);
                kurang6.setVisibility(View.GONE);
                erroremail.setVisibility(View.GONE);
            }
        } else {
            erroremail.setVisibility(View.VISIBLE);

        }


    }


}
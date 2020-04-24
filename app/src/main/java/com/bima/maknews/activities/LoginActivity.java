package com.bima.maknews.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bima.maknews.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mEmail,mPassword;
    private static final String TAG = "LoginActivity";
    TextView emailsalah,errorlogin;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText)findViewById(R.id.edtEmail);
        mPassword = (EditText)findViewById(R.id.edtPassword);
        emailsalah = (TextView) findViewById(R.id.emailerror);
        errorlogin = (TextView) findViewById(R.id.eror);
        loading = new ProgressDialog(this);
        loading.setTitle("Mohon tunggu");
        loading.setCancelable(false);
        loading.setMessage("Sedang memproses data");
        loading.dismiss();

        emailsalah.setVisibility(View.GONE);
        errorlogin.setVisibility(View.GONE);

    }

    public void login(View view){
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        if (email.contains("@")){
            loading.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                loading.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent x = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(x);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                loading.dismiss();
                                errorlogin.setVisibility(View.VISIBLE);
                                emailsalah.setVisibility(View.GONE);

                            }

                            // ...
                        }
                    });

        }else{
            emailsalah.setVisibility(View.VISIBLE);
            errorlogin.setVisibility(View.GONE);
        }
    }

    public void signUp(View view) {
        Intent x=new Intent(this,RegistrasiActivity.class);
        startActivity(x);
    }
}

package com.example.doan_ltddnc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextView;
    TextInputEditText tvtdn,edtmk;
    Button btnDN;
    FirebaseAuth auth;
    TextView forget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();


        tvtdn = findViewById(R.id.tvtdn);
        forget=findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,QuenMkActivity.class);
                startActivity(intent);
            }
        });

        edtmk= findViewById(R.id.edtmk);
        btnDN=findViewById(R.id.btnDN);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email3=tvtdn.getText().toString().trim();
                String password=edtmk.getText().toString().trim();





                if(TextUtils.isEmpty( email3))
                {
                    tvtdn.setError("Vui long dien email");


                }
                else if(TextUtils.isEmpty( password))
                {
                    edtmk.setError("Vui long dien password");

                }
                else if(password.length()<6)
                {
                    edtmk.setError("Mat khau nhieu hon 6 ky tu");

                }
                else if(!email3.contains("@")){
                    tvtdn.setError("Email phai có ký tự @");


                }
                else {


                    auth.signInWithEmailAndPassword(email3, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginActivity.this, "Đăng nhập Không thành công", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });







    }
}
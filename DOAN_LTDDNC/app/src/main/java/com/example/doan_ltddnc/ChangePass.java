package com.example.doan_ltddnc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePass extends AppCompatActivity {

    TextInputEditText tvPassword, tvConfirmPassword;
    Button btnChange;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvPassword = findViewById(R.id.tvPassword);
        tvConfirmPassword = findViewById(R.id.tvConfirmPassword);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btnChange = findViewById(R.id.btnChangePassword);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(tvPassword.getText().toString()) && !TextUtils.isEmpty(tvConfirmPassword.getText().toString())){
                    if (tvConfirmPassword.getText().toString().equals(tvPassword.getText().toString())){
                        firebaseUser.updatePassword(tvPassword.getText().toString());
                        Toast.makeText(getApplicationContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePass.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Vui lòng nhâp lại đúng mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
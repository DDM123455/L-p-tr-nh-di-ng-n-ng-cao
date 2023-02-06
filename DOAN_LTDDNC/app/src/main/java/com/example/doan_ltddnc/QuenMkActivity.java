package com.example.doan_ltddnc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMkActivity extends AppCompatActivity {

    EditText editTextTextEmailAddress;
    Button btnquenmk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        btnquenmk=findViewById(R.id.btnquenmk);

        btnquenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String gmaill = editTextTextEmailAddress.getText().toString();

                auth.sendPasswordResetEmail(gmaill)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Thư xác nhận đã được gửi tới gmail của bạn",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
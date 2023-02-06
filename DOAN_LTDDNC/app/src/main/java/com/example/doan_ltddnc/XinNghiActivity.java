package com.example.doan_ltddnc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class XinNghiActivity extends AppCompatActivity {

    TextInputEditText edtTieuDe,edtld;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    Button btnsend;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_nghi);

       edtTieuDe=findViewById(R.id.edtTieuDe);
        edtld=findViewById(R.id.edtlydo);
        btnsend=findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();


                String td=edtTieuDe.getText().toString();
                String ld=edtld.getText().toString();
                hashMap.put("td", td);
                hashMap.put("ld", ld);
                fAuth = FirebaseAuth.getInstance();


                UserID=fAuth.getCurrentUser().getUid();



                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("QuanLyNv").child("123").child(UserID);
                ref.setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                HashMap<String,String> hashMap1 = new HashMap<>();


                            }
                        });
            }
        });

    }
}
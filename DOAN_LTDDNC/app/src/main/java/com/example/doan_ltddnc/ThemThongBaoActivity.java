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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ThemThongBaoActivity extends AppCompatActivity {
    TextInputEditText tdtb,tdnd,matb;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    Button btnTB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thong_bao);
        fAuth=FirebaseAuth.getInstance();

        tdtb=findViewById(R.id.tdtb);
        tdnd=findViewById(R.id.ndtb);
        matb=findViewById(R.id.matb);
        btnTB=findViewById(R.id.btnThemTB);
        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();
                String matb2=matb.getText().toString();

                String td = tdtb.getText().toString();
                String nd = tdnd.getText().toString();
                String tg = new SimpleDateFormat("MM:yyyy", Locale.getDefault()).format(new Date());

                /* phongId = fAuth.getUid();*/
                hashMap.put("tieuDe", nd);
                hashMap.put("Nd", nd);
                hashMap.put("ngay",tg);
                hashMap.put("thongBaoId",matb2);
                Boolean trangthai=true;


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("QuanLyNv").child("1").child(fAuth.getUid()).child("Notifications").child(matb2);
                ref.setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                HashMap<String,String> hashMap1 = new HashMap<>();
                                hashMap1.put("tieuDe", nd);
                                hashMap1.put("Nd", nd);
                                hashMap1.put("ngay",tg);
                                hashMap1.put("thongBaoId",matb2);
                                hashMap1.put("trangthai",trangthai.toString());


                            }
                        });
            }
        });


    }
}
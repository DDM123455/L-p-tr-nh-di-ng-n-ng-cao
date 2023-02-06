package com.example.doan_ltddnc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_ltddnc.Model.DilamModel;
import com.example.doan_ltddnc.Model.NhanVienModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LuongActivity extends AppCompatActivity {
    TextView luongthang,luongcoban,Songaydi,Songaytre;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference reference;
    DilamModel dilamModel;
    String thang ;
    String songaytre, songaydi,months;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luong);
        luongthang=findViewById(R.id.luongthang);
        luongcoban=findViewById(R.id.luongcoban);
        Songaydi=findViewById(R.id.Songaydi);
        Songaytre=findViewById(R.id.Songaytre);

        Intent intent = getIntent();
        DilamModel dilamModel = (DilamModel) getIntent().getSerializableExtra("month");
//
//        songaydi=intent.getStringExtra("Songaydi");
//        songaytre=intent.getStringExtra("Songaytre");
//        months=intent.getStringExtra("months");
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        DatabaseReference reference = database.getReference("QuanLyNv").child("1");
        reference.child(auth.getUid()).child("Dilam").child(dilamModel.getMonths()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    DilamModel dilamModel1 = task.getResult().getValue(DilamModel.class);
                    Songaydi.setText(String.valueOf(dilamModel1.getSongaydi()));
                    Songaytre.setText(String.valueOf(dilamModel1.getSongaytre()));

                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        DatabaseReference reference2 = database.getReference("QuanLyNv").child("1");
        reference2.child(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    NhanVienModel nhanVienModel = task.getResult().getValue(NhanVienModel.class);
                    luongcoban.setText(nhanVienModel.getLuongNgay());
                    int a=Integer.valueOf(nhanVienModel.getLuongNgay());
                    DatabaseReference reference3 = database.getReference("QuanLyNv").child("1");
                    reference3.child(auth.getUid()).child("Dilam").child(dilamModel.getMonths()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()){


                                DilamModel dilamModel1 = task.getResult().getValue(DilamModel.class);
                                int b=Integer.valueOf(dilamModel1.getSongaydi());
                                int c=Integer.valueOf(dilamModel1.getSongaytre());
                                int luong=(a*b)-(20000*c);
                                luongthang.setText(String.valueOf(luong));


                            }else{
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            }
                        }
                    });


                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });


    }




}
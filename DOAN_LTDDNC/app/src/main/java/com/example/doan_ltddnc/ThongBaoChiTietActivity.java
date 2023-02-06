package com.example.doan_ltddnc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_ltddnc.Model.ThongBaoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ThongBaoChiTietActivity extends AppCompatActivity {
    TextView tieude,nd,ngay;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference reference;
    ThongBaoModel thongBaoModel;
    String thang ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_chi_tiet);

        tieude=findViewById(R.id.tieudee);
        nd=findViewById(R.id.ndd);
        ngay=findViewById(R.id.ngayy);

        Intent intent = getIntent();
        ThongBaoModel thongBaoModel = (ThongBaoModel) getIntent().getSerializableExtra("thongBaoId");
//
//        songaydi=intent.getStringExtra("Songaydi");
//        songaytre=intent.getStringExtra("Songaytre");
//        months=intent.getStringExtra("months");
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        DatabaseReference reference = database.getReference("QuanLyNv").child("1");
        reference.child(auth.getUid()).child("Notifications").child(thongBaoModel.getThongBaoId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){


                    ThongBaoModel thongBaoModel1 = task.getResult().getValue(ThongBaoModel.class);
                    tieude.setText(String.valueOf(thongBaoModel1.getTieuDe()));
                    nd.setText(String.valueOf(thongBaoModel1.getNd()));
                    ngay.setText(String.valueOf(thongBaoModel1.getNgay()));

                    Map<String,Object> user = new HashMap<>();
                    Boolean trangthai=false;
                    user.put("trangthai",trangthai);

                    reference.child(auth.getUid()).child("Notifications").child(thongBaoModel.getThongBaoId()).updateChildren(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

//
                                }
                            });




                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }
}
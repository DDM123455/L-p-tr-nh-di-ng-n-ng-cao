package com.example.doan_ltddnc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Adapter.ThongBaoAdapter;
import com.example.doan_ltddnc.Model.ThongBaoModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThongBaoActivity extends AppCompatActivity implements ThongBaoAdapter.OnThongBaoItemClickListener  {



    TextInputEditText td,nd,ngay;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    int i=0;
    ImageView delete;

    Button btndelete;
    View tb;



    RecyclerView rvtb;
    ThongBaoAdapter thongBaoAdapter;
    ArrayList<ThongBaoModel> thongBaoModels;
    EditText searchEdt;
    DatabaseReference dtb;
    DatabaseReference dtb2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        td=findViewById(R.id.tdtb);
        nd=findViewById(R.id.ndtb);
        btndelete=findViewById(R.id.delete);
        ngay=findViewById(R.id.ngay);
        fAuth=FirebaseAuth.getInstance();
        thongBaoModels=new ArrayList<>();

        rvtb = findViewById(R.id.rvTB);

        thongBaoAdapter = new ThongBaoAdapter(thongBaoModels, this,getApplicationContext());
        rvtb.setAdapter(thongBaoAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvtb.setLayoutManager(layoutManager);
        rvtb.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
//        String maPhong = TvMaPhong.getText().toString();

        fDatabase = FirebaseDatabase.getInstance();
        dtb = fDatabase.getReference("QuanLyNv").child("1").child(fAuth.getUid()).child("Notifications");
        Query qPhong = dtb;
        dtb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thongBaoModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ThongBaoModel thongBaoModel = dataSnapshot.getValue(ThongBaoModel.class);
                    thongBaoModels.add(thongBaoModel);
                    int a= (int) snapshot.getChildrenCount();
                    thongBaoModels.size();
//                    Intent intent = new Intent(ThongBaoActivity.this, MainActivity.class);
//                    intent.putExtra("so", a);
////                    startActivity(intent);

//                    Log.d("a", String.valueOf(a));


                }
                thongBaoAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        fDatabase = FirebaseDatabase.getInstance();
        dtb = fDatabase.getReference("QuanLyNv").child("1").child(fAuth.getUid()).child("Notifications");

//        btndelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dtb.removeValue(new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        Toast.makeText(ThongBaoActivity.this,"Oke",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });






    }

    @Override
    public void onThongBaomItemClick(ThongBaoModel thongBaoModel) {
        Intent intent=new Intent(this,ThongBaoChiTietActivity.class);
        intent.putExtra("thongBaoId",thongBaoModel);
        startActivity(intent);
    }


}
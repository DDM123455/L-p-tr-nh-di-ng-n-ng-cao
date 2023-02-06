package com.example.doan_ltddnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Adapter.DilamAdapter;
import com.example.doan_ltddnc.Model.DilamModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TinhLuongActivity extends AppCompatActivity implements DilamAdapter.OnDilamItemClickListener {
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    Button btnTenPhong;
    String phongId;
    TextView thang;

    RecyclerView rvDilam;
    DilamAdapter dilamAdapter;
    ArrayList<DilamModel> dilams;
    EditText searchEdt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_luong);
        thang=findViewById(R.id.thang);
        fAuth=FirebaseAuth.getInstance();
        dilams=new ArrayList<>();




        rvDilam = findViewById(R.id.dilam);

        dilamAdapter = new DilamAdapter(dilams, this::onDIlamItemClick);
        rvDilam.setAdapter(dilamAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvDilam.setLayoutManager(layoutManager);
        rvDilam.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        fDatabase = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = fDatabase.getReference("QuanLyNv");
        Query qDilam = databaseReference.child("1").child(uid).child("Dilam");
        qDilam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dilams.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DilamModel dilamModel = dataSnapshot.getValue(DilamModel.class);
                    dilams.add(dilamModel);
                }
                dilamAdapter.notifyDataSetChanged();

                rvDilam.setAdapter(dilamAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }


    public static int getMonthDays(int month, int year) {
        int daysInMonth ;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        }
        else {
            if (month == 2) {
                daysInMonth = (year % 4 == 0) ? 29 : 28;
            } else {
                daysInMonth = 31;
            }
        }
        return daysInMonth;
    }

    @Override
    public void onDIlamItemClick(DilamModel dilamModel) {
        Intent intent=new Intent(this,LuongActivity.class);
        intent.putExtra("month",dilamModel);
        startActivity(intent);
    }
}
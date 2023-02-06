package com.example.doan_ltddnc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Adapter.NhanVienAdapter;
import com.example.doan_ltddnc.Model.NhanVienModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimNVActivity extends AppCompatActivity {


    RecyclerView rvnhanvien;
    NhanVienAdapter nhanVienAdapter;
    ArrayList<NhanVienModel> nhanVienModels;
    EditText searchnv;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    DatabaseReference dnv;
    TextView ten,gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_nvactivity);

        nhanVienModels = new ArrayList<>();
        searchnv=findViewById(R.id.searchnv);

        rvnhanvien = findViewById(R.id.timnv);


        ten=findViewById(R.id.tennv);
        gmail=findViewById(R.id.gmailnv);

        fAuth=FirebaseAuth.getInstance();





        String id=fAuth.getUid();


        nhanVienAdapter = new NhanVienAdapter(nhanVienModels, this);
        rvnhanvien.setAdapter(nhanVienAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvnhanvien.setLayoutManager(layoutManager);
        rvnhanvien.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        fDatabase = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dnv = fDatabase.getReference("QuanLyNv");
        searchnv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchPhong(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Query qnv = dnv.child("1");
        qnv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nhanVienModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NhanVienModel nhanVienModel = dataSnapshot.getValue(NhanVienModel.class);
                    nhanVienModels.add(nhanVienModel);

                }
                nhanVienAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    private void searchPhong(String s) {
        nhanVienModels = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("QuanLyNv").child("1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nhanVienModels.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    //search by group title
                    if (ds.child("nv").toString().toLowerCase().contains(s.toLowerCase())) {
                        NhanVienModel nhanVienModel = ds.getValue(NhanVienModel.class);
                        nhanVienModels.add(nhanVienModel);

                    }
                }
                nhanVienAdapter = new NhanVienAdapter(nhanVienModels,getApplicationContext());
                rvnhanvien.setAdapter(nhanVienAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}}
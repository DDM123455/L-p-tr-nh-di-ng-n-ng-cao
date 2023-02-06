package com.example.doan_ltddnc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Adapter.PhongAdapter;
import com.example.doan_ltddnc.Model.PhongModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemPhongActivity extends AppCompatActivity {
    TextInputEditText TvTenPhong,TvMaPhong;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    Button btnTenPhong;
    String phongId;

    RecyclerView rvPhong;
    PhongAdapter phongAdapter;
    ArrayList<PhongModel> phongModels;
    EditText searchEdt;
    DatabaseReference dphong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong);

            TvTenPhong = findViewById(R.id.TenPhong);
        fAuth=FirebaseAuth.getInstance();
        TvMaPhong = findViewById(R.id.TvMaPhong);
        btnTenPhong = findViewById(R.id.btnTenPhong);
        phongModels = new ArrayList<>();
        searchEdt = findViewById(R.id.searchEdt);
        searchEdt.addTextChangedListener(new TextWatcher() {
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
        btnTenPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();

                String tenphong = TvTenPhong.getText().toString();
                String maPhong = TvMaPhong.getText().toString();
                hashMap.put("tenPhong", tenphong);
               /* phongId = fAuth.getUid();*/
                hashMap.put("phongId", maPhong);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("QuanLyNv");
                ref.child(maPhong).setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                HashMap<String,String> hashMap1 = new HashMap<>();

                            }
                        });
            }


        });
        rvPhong = findViewById(R.id.home_phong_rv);

        phongAdapter = new PhongAdapter(phongModels, this);
        rvPhong.setAdapter(phongAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvPhong.setLayoutManager(layoutManager);
        rvPhong.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        String maPhong = TvMaPhong.getText().toString();

        fDatabase = FirebaseDatabase.getInstance();
        dphong = fDatabase.getReference("QuanLyNv");
        Query qPhong = dphong.child(maPhong);
        qPhong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phongModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PhongModel phongModel = dataSnapshot.getValue(PhongModel.class);
                    phongModels.add(phongModel);
                }
                phongAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
    });

}

    private void searchPhong(String s) {
        phongModels = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("QuanLyNv");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phongModels.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                        //search by group title
                        if (ds.child("phongId").toString().toLowerCase().contains(s.toLowerCase())) {
                            PhongModel phongModel = ds.getValue(PhongModel.class);
                            phongModels.add(phongModel);

                        }
                }
                phongAdapter = new PhongAdapter(phongModels,getApplicationContext());
                rvPhong.setAdapter(phongAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

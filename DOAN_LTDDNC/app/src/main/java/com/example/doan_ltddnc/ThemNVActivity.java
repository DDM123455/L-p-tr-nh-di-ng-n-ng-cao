package com.example.doan_ltddnc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ThemNVActivity extends AppCompatActivity {
    TextInputEditText edtMaPhong,LuongNgayy,edtGmail,edtmk,edtnv,DiaChi,NgaySinh,SDT;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    Button btnThemNV;
    String NvID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nvactivity);
        edtMaPhong=findViewById(R.id.edtMaPhong);
        LuongNgayy=findViewById(R.id.LuongNgayy);
        edtGmail=findViewById(R.id.edtGmail);
        edtmk=findViewById(R.id.edtmk);
        edtnv=findViewById(R.id.edtnv);
        fAuth = FirebaseAuth.getInstance();
        btnThemNV = findViewById(R.id.btnThemNV);
        DiaChi=findViewById(R.id.DiaChi);
        SDT=findViewById(R.id.SDT);
        NgaySinh=findViewById(R.id.NgaySinh);

        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();

               String maPhong=edtMaPhong.getText().toString();
                String gmail=edtGmail.getText().toString();
                String mk=edtmk.getText().toString();
                String nv=edtnv.getText().toString();
                String LuongNgay = LuongNgayy.getText().toString();

                String DiaChi1 = DiaChi.getText().toString();
                String SDT1 = SDT.getText().toString();
                String NgaySinh1 = NgaySinh.getText().toString();
                Object Dilam = null;

                int Songaytre =0;
                int Songaydi =0;


                hashMap.put("maPhong", maPhong);
//                hashMap.put("Songaytre", Songaytre);
//                hashMap.put("Songaydi", Songaydi);
//                int thang=5;

                hashMap.put("DiaChi", DiaChi1);
                hashMap.put("SDT", SDT1);
                hashMap.put("NgaySinh", NgaySinh1);

                hashMap.put("Dilam", Dilam);







                hashMap.put("LuongNgay", LuongNgay);
                hashMap.put("nv",nv);
                hashMap.put("mk", mk);
                fAuth.createUserWithEmailAndPassword(gmail, mk)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isComplete()) {
//                                    NvID = fAuth.getCurrentUser().getUid();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("QuanLyNv").child(maPhong);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("gmail", gmail);
                                    user.put("maPhong", maPhong);
//                                    user.put("Songaytre",Songaytre);
//                                    user.put("Songaydi", Songaydi);
                                    user.put("Dilam", Dilam);


                                    user.put("DiaChi", DiaChi1);
                                    user.put("SDT", SDT1);
                                    user.put("NgaySinh", NgaySinh1);



                                    user.put("LuongNgay", LuongNgay);
                                    user.put("nv",nv);
                                    databaseReference.child(fAuth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            user.put("gmail", gmail);
                                            user.put("maPhong", maPhong);
//                                            user.put("Songaytre",Songaytre);
//                                            user.put("Songaydi", Songaydi);
                                            user.put("Dilam", Dilam);


                                            user.put("DiaChi", DiaChi1);
                                            user.put("SDT", SDT1);
                                            user.put("NgaySinh", NgaySinh1);




                                            user.put("LuongNgay", LuongNgay);
                                            user.put("nv",nv);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
                                }
                            }
                        });
                                           /* databaseReference.child("QuanLyNv/123").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    user.put("Manv",Manv);
                                                    user.put("maPhong",maPhong);
                                                    user.put("tenNv",tenNv);
                                                    user.put("LuongNgay",LuongNgay);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

*/

                /* phongId = fAuth.getUid();*/
            }
        });
    }
}
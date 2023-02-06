package com.example.doan_ltddnc;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserNamePassActivity extends AppCompatActivity {
    Button btnchangepass,btnupdate;
    EditText gmail;
    MainActivity mainActivity;
    MainActivity fact = new MainActivity();


    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_pass);

        btnchangepass=findViewById(R.id.btnchangepass);
        btnupdate=findViewById(R.id.btnupdate);
        gmail=findViewById(R.id.gmail);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        DatabaseReference reference = database.getReference("QuanLyNv").child("1");
        reference.child(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    gmail.setText(user.getEmail());



                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });







        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserNamePassActivity.this,ChangePass.class);
                startActivity(intent);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gmailnew=gmail.getText().toString().trim();
                gmail.setText(gmailnew);
                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                firebaseUser.updateEmail(gmailnew).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User Gmail updated.");
                            Toast.makeText(UserNamePassActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();





                        }
                    }
                });


            }
        });

    }

}
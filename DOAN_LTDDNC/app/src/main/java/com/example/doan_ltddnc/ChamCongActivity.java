package com.example.doan_ltddnc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChamCongActivity extends AppCompatActivity {
    TextView ngay, Tgvao, Tgra;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    CardView ChamCong;
    String hour;
    int count=0;


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_cong);

        ChamCong = findViewById(R.id.ChamCong);


        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        ChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time1 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                String time2 = "9:00";
                String months = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());

                int Songaytre = 0;
                 int Songaydi=0;
                HashMap<String, Object> hashMap = new HashMap<>();
                int one =1 ;
                {
                if (CompareTime(time1, time2) == true) {

                    count++;
                    Songaydi=Songaydi+count;

                    hashMap.put("Songaydi",Songaydi);
                    Toast.makeText(getApplicationContext(),"Chấm công thành công",Toast.LENGTH_LONG).show();



                } else {


                    count++;
                    Songaytre=Songaytre+count;

                    hashMap.put("Songaytre",Songaytre);
                    Toast.makeText(getApplicationContext(),"Bạn đã đi trễ",Toast.LENGTH_LONG).show();



                }
                    hashMap.put("months",months);

                }

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("QuanLyNv").child("1").child(uid).child("Dilam").child(months);
                ref.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {


                        }

                    }
                });




            }
        });
    }

    void setFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {

            case 1:
                fragment = new RaFragment();
                break;

        }

    }

    public Boolean CompareTime(String timeone,String timeTwo) {
        String pattern="HH:mm";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);

        try {
            Date firstTime=sdf.parse(timeone);
            Date secondtime=sdf.parse(timeTwo);
            if (firstTime.before(secondtime))
            {
                return true;

            }
            else{
            return false;}

        }
        catch (Exception e)
        {

        }
        return false;


    }


}
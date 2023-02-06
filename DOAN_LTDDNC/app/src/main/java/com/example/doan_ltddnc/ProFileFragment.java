package com.example.doan_ltddnc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_ltddnc.Model.NhanVienModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProFileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProFileFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseAuth auth;
    Button btnupdate;
    EditText profile_ten,profile_sdt,profile_diachi,profile_ngaysinh;
    String id;
    TextView profile_gmail,profile_maphong;
    DatabaseReference reference;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProFileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProFileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProFileFragment newInstance(String param1, String param2) {
        ProFileFragment fragment = new ProFileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pro_file, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile_gmail= view.findViewById(R.id.profile_gmail);
        profile_maphong=view.findViewById(R.id.profile_maphong);
        profile_ten=view.findViewById(R.id.profile_ten);

        profile_diachi= view.findViewById(R.id.profile_diachi);
        profile_ngaysinh=view.findViewById(R.id.profile_ngaysinh);
        profile_sdt=view.findViewById(R.id.profile_sdt);
        btnupdate=view.findViewById(R.id.btnupdate);

//        String id=new NhanVienModel(.getIdPhong());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        DatabaseReference reference = database.getReference("QuanLyNv").child("1");
        reference.child(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    NhanVienModel user = task.getResult().getValue(NhanVienModel.class);
                    profile_gmail.setText(user.getGmail());
                    profile_maphong.setText(user.getmaPhong());
                    profile_ten.setText(user.getnv());

                    profile_diachi.setText(user.getDiaChi());
                    profile_ngaysinh.setText(user.getNgaySinh());
                    profile_sdt.setText(user.getSDT());


                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).setTitle("Bạn có muốn thay đổi không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Update(profile_ten.getText().toString(),profile_diachi.getText().toString()
                                        ,profile_sdt.getText().toString(),profile_ngaysinh.getText().toString(),profile_gmail.getText().toString(),
                                        profile_maphong.getText().toString());
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "Đã hủy",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();

            }
        });



    }

    private void Update(String name,String diachi, String sdt,String ngaysinh,String gmail,String phong){
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        DatabaseReference reference = database.getReference();

        Map<String,Object> user = new HashMap<>();
        user.put("nv",name);
        user.put("DiaChi",diachi);
        user.put("SDT",sdt);
        user.put("NgaySinh",ngaysinh);
        user.put("maPhong",phong);
        user.put("gmail",gmail);


        reference.child("QuanLyNv").child("1").child(auth.getUid()).updateChildren(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Cập nhật thành công",
                                Toast.LENGTH_SHORT).show();

//                        NavigationView nav = getActivity().findViewById(R.id.navView);
////                        View view  = nav.getHeaderView(0);
////                        TextView name = view.findViewById(R.id.Tvname);
////                        name.setText((CharSequence) name);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
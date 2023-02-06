package com.example.doan_ltddnc.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Model.ThongBaoModel;
import com.example.doan_ltddnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolderThongBao> {
    public ThongBaoAdapter(List<ThongBaoModel> thongBaoModels, OnThongBaoItemClickListener mListener,Context context) {
        this.thongBaoModels = thongBaoModels;
        this.mListener = mListener;
        this.context=context;
    }
    Context context;
    int count=0;

    private List<ThongBaoModel> thongBaoModels;
    private ThongBaoAdapter.OnThongBaoItemClickListener mListener;
    private ThongBaoAdapter.OnDeleteClickItem onDeleteClickItem;

    @NonNull
    @Override
    public ViewHolderThongBao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new ViewHolderThongBao(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThongBao holder, int position) {
        ThongBaoModel thongBaoModel=thongBaoModels.get(position);
        String ngay2=thongBaoModel.getNgay().toString().trim();
        holder.ngay.setText(ngay2);
        int m=position;
        String m2= String.valueOf(m);

        String td=thongBaoModel.getTieuDe().toString().trim();
        holder.td.setText(td);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference reference = database.getReference("QuanLyNv").child("1");
        reference.child(auth.getUid()).child("Notifications").child(thongBaoModel.getThongBaoId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    ThongBaoModel user = task.getResult().getValue(ThongBaoModel.class);
                    if(user.getTrangthai()!=null) {
                        if (user.getTrangthai() == false) {
                            holder.itemView.setBackgroundColor(Color.parseColor("#B0C4DE"));
                        } else {
                            holder.itemView.setBackgroundColor(Color.parseColor("#F0FFFF"));

                        }
                    }
                    else {
                        return;
                    }



                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

        String nd=thongBaoModel.getNd().toString().trim();
        holder.nd.setText(nd);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thongBaoModels.remove(m);
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("QuanLyNv").child("1").child(FirebaseAuth.getInstance().getUid()).child("Notifications").child(thongBaoModel.getThongBaoId());
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(context,"oke",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
//                thongBaoModels.remove(m);

                notifyDataSetChanged();
//                mListener.onThongBaomItemClick(thongBaoModel);
//                            holder.itemView.setVisibility(View.GONE);

            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onThongBaomItemClick(thongBaoModel);


            }
        });







    }

    @Override
    public int getItemCount() {
        return thongBaoModels.size();
    }
    public int ItemCount() {
        return thongBaoModels.size();
    }



    public interface OnThongBaoItemClickListener {
        void onThongBaomItemClick(ThongBaoModel thongBaoModel);

    }


    public interface OnDeleteClickItem {
        void ondeleteItemClick(ThongBaoModel thongBaoModel);

    }


    public class ViewHolderThongBao extends RecyclerView.ViewHolder {
        TextView td,nd,ngay;
        Button delete;
        RelativeLayout relativeLayout;
        public ViewHolderThongBao(View itemView) {
            super(itemView);
            td=itemView.findViewById(R.id.td);
            nd=itemView.findViewById(R.id.nd);
            ngay=itemView.findViewById(R.id.ngay);
            delete=itemView.findViewById(R.id.delete);
            relativeLayout=itemView.findViewById(R.id.relative);
        }
    }
}

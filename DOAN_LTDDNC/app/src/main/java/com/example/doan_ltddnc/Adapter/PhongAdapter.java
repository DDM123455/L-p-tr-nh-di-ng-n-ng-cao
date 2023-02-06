package com.example.doan_ltddnc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Model.PhongModel;
import com.example.doan_ltddnc.R;

import java.util.ArrayList;
import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolderPhong> {
    private List<PhongModel> mphongModels;
    private Context context;

    public PhongAdapter(ArrayList<PhongModel> mphongModels, Context context) {
        this.mphongModels = mphongModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderPhong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

  /*      if(TYPE_LAYOUT == 1){*/
            View view = LayoutInflater.from(context).inflate(R.layout.phongitem, parent, false);
            return new ViewHolderPhong(view);
//        }
       /* else
            return null;*/
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPhong holder, int position) {
        PhongModel phongModel=mphongModels.get(position);
        String tenPB = phongModel.getTenPhong();
        String maPB = phongModel.getPhongId();
        holder.tv_item_tenphong.setText(tenPB);
        holder.tv_item_maphong.setText(maPB);
    }


    @Override
    public int getItemCount() {
            return mphongModels.size();
    }

    public static class ViewHolderPhong extends RecyclerView.ViewHolder {

        TextView tv_item_maphong,tv_item_tenphong;
        public ViewHolderPhong(View itemView) {
            super(itemView);
            tv_item_tenphong=itemView.findViewById(R.id.tv_item_tenphong);
            tv_item_maphong=itemView.findViewById(R.id.tv_item_maphong);
        }
    }
}

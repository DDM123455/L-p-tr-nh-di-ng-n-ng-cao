package com.example.doan_ltddnc.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChamCongAdapter extends RecyclerView.Adapter<XinNghiAdapter.ViewHolderXinNghi>{

    @NonNull
    @Override
    public XinNghiAdapter.ViewHolderXinNghi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull XinNghiAdapter.ViewHolderXinNghi holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderChamCong extends RecyclerView.ViewHolder {
        TextView ngay,tgvao,tgra;
        public ViewHolderChamCong(View itemView) {
            super(itemView);

        }
    }

}

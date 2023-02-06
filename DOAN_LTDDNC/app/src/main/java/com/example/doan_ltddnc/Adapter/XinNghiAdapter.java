package com.example.doan_ltddnc.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.R;

public class XinNghiAdapter extends RecyclerView.Adapter<XinNghiAdapter.ViewHolderXinNghi> {
    @NonNull
    @Override
    public ViewHolderXinNghi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderXinNghi holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderXinNghi extends RecyclerView.ViewHolder {
        EditText edtld,edttd;
        public ViewHolderXinNghi(View itemView) {
            super(itemView);
            edttd=itemView.findViewById(R.id.edtTieuDe);
            edtld=itemView.findViewById(R.id.edtlydo);
        }
    }
}

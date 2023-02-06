package com.example.doan_ltddnc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Model.DilamModel;
import com.example.doan_ltddnc.R;

import java.util.List;

public class DilamAdapter extends RecyclerView.Adapter<DilamAdapter.ViewHolderDilam>{
    private List<DilamModel> dilams;
    private DilamAdapter.OnDilamItemClickListener mListener;


    public DilamAdapter(List<DilamModel> dilams, OnDilamItemClickListener mListener) {
        this.dilams = dilams;
        this.mListener = mListener;
    }



    @NonNull
    @Override
    public ViewHolderDilam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thangitem, parent, false);
        return new ViewHolderDilam(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDilam holder, int position) {
        DilamModel dilamModel=dilams.get(position);
        String thangn = dilamModel.getMonths();
        holder.thang.setText(thangn.toString());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDIlamItemClick(dilamModel);

            }
        });




    }

    @Override
    public int getItemCount() {
        return dilams.size();
    }

    public interface OnDilamItemClickListener {
        void onDIlamItemClick(DilamModel dilamModel);
    }

    public class ViewHolderDilam extends RecyclerView.ViewHolder {
        TextView thang;
        RelativeLayout layoutitem;
        TextView Songaydi,Songaytre;
        public ViewHolderDilam(View itemView) {
            super(itemView);
            thang=itemView.findViewById(R.id.thang);
            layoutitem=itemView.findViewById(R.id.layoutitem);
            Songaydi=itemView.findViewById(R.id.Songaydi);
            Songaytre=itemView.findViewById(R.id.Songaytre);
        }
    }


}

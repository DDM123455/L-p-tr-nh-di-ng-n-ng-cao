package com.example.doan_ltddnc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_ltddnc.Model.NhanVienModel;
import com.example.doan_ltddnc.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolderNhanVien>{


    public NhanVienAdapter(List<NhanVienModel> nhanVienModels, Context context) {
        this.nhanVienModels = nhanVienModels;
        this.context = context;
    }

    private List<NhanVienModel> nhanVienModels;
    private Context context;
    @NonNull
    @Override
    public ViewHolderNhanVien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nvitem, parent, false);
        return new ViewHolderNhanVien(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNhanVien holder, int position) {

        NhanVienModel nhanVienModel=nhanVienModels.get(position);
        String ten = nhanVienModel.getnv().trim();
        String gmaill = nhanVienModel.getGmail().trim();
        holder.tennv.setText(ten);
        holder.diachi.setText(gmaill);

    }

    @Override
    public int getItemCount() {
        return nhanVienModels.size();
    }

    public class ViewHolderNhanVien extends RecyclerView.ViewHolder {
        TextInputEditText LuongNgayy,edtmk,edtnv,SDT,NgaySinh,DiaChi;
        TextView edtMaPhong,edtGmail,tennv,diachi;



        public ViewHolderNhanVien(View itemView) {
            super(itemView);
            edtMaPhong=itemView.findViewById(R.id.edtMaPhong);
            LuongNgayy=itemView.findViewById(R.id.LuongNgayy);
            edtGmail=itemView.findViewById(R.id.edtGmail);
            edtmk=itemView.findViewById(R.id.edtmk);
            edtnv=itemView.findViewById(R.id.edtnv);
            SDT=itemView.findViewById(R.id.SDT);
            NgaySinh=itemView.findViewById(R.id.NgaySinh);
            DiaChi=itemView.findViewById(R.id.DiaChi);


            tennv=itemView.findViewById(R.id.tennv);
            diachi=itemView.findViewById(R.id.gmailnv);


        }
    }
}

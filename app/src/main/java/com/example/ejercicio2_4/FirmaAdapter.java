package com.example.ejercicio2_4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio2_4.db.entidades.Signaturess;

import java.util.List;

public class FirmaAdapter extends RecyclerView.Adapter<FirmaAdapter.ViewHolder> {

    private List<Signaturess> signatureModelList;

    public FirmaAdapter(List<Signaturess> signatureModelList) {
        this.signatureModelList = signatureModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firma, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = signatureModelList.get(position).getDescripcion();

        byte[] firma = signatureModelList.get(position).getFirma();
        Bitmap imgfirma = BitmapFactory.decodeByteArray(firma, 0, firma.length);

        holder.tvFirma.setText(name);
        holder.ivFirma.setImageBitmap(imgfirma);
    }

    @Override
    public int getItemCount() {
        return signatureModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFirma;
        private ImageView ivFirma;
        public ViewHolder(View v) {
            super(v);
            tvFirma = (TextView) v.findViewById(R.id.tvFirma);
            ivFirma = (ImageView) v.findViewById(R.id.ivFirma);
        }
    }

}

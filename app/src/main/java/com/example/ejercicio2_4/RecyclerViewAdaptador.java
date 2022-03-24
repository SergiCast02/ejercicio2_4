package com.example.ejercicio2_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio2_4.db.entidades.Signaturess;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descripcion, id;
        ImageView fotoFirma;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.tvId);
            descripcion = (TextView) itemView.findViewById(R.id.tvFirma);
            fotoFirma = (ImageView) itemView.findViewById(R.id.imgFirma);
        }
    }

    public List<Signaturess> firmaLista;

    public RecyclerViewAdaptador (List<Signaturess> firmaLista) {
        this.firmaLista = firmaLista;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firma, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.id.setText(firmaLista.get(position).getId());
        holder.descripcion.setText(firmaLista.get(position).getDescripcion());
        holder.fotoFirma.setImageResource(firmaLista.get(position).getId());
    }

    @Override
    public int getItemCount () {
        return firmaLista.size();
    }

}

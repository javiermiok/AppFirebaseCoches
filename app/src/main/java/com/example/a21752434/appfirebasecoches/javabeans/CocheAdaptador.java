package com.example.a21752434.appfirebasecoches.javabeans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21752434.appfirebasecoches.R;

import java.util.ArrayList;

public class CocheAdaptador extends RecyclerView.Adapter<CocheAdaptador.CocheViewHolder>
        implements View.OnClickListener{

    private ArrayList<Coche> lista;
    private Context contexto;
    private View.OnClickListener listener;

    public CocheAdaptador(ArrayList<Coche> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    /*-------------------          MÉTODOS NECESARIOS                 --------------------- */
    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coche, viewGroup, false);
        v.setOnClickListener(this);
        CocheViewHolder cvh = new CocheViewHolder(v, contexto);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder cocheViewHolder, int i) {
        cocheViewHolder.bindCoche(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void clear() {
        lista.clear();
    }

    /*-------------------------           CLASE INTERNA           -------------------------------*/
    public class CocheViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        private TextView tvMatricula;
        private TextView tvModelo;
        private TextView tvColor;
        private TextView tvAnio;
        private TextView tvPrecio;


        public CocheViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvMatricula = itemView.findViewById(R.id.tvMatriculaItem);
            tvModelo = itemView.findViewById(R.id.tvModeloItem) ;
            tvColor = itemView.findViewById(R.id.tvColorItem);
            tvAnio = itemView.findViewById(R.id.tvAnioItem);
            tvPrecio = itemView.findViewById(R.id.tvPrecioItem);

        }

        public void bindCoche(Coche c) {
            tvMatricula.setText(String.format(context.getString(R.string.tv_matricula_item), c.getMatricula()));
            tvModelo.setText(String.format(context.getString(R.string.tv_modelo_item), c.getModelo()));
            tvColor.setText(String.format(context.getString(R.string.tv_color_item), c.getColor()));
            tvAnio.setText(String.format(context.getString(R.string.tv_anio_item), c.getAnio()));
            tvPrecio.setText(String.format(context.getString(R.string.tv_precio_item), c.getPrecio()));
        }
    }

}

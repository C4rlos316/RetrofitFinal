package com.prueba.retrofitfinal.Prueba2Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.retrofitfinal.R;

import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.UsuarioHolder> {

    List<PacienteExample> usuarios;

    public AdapterUsuario(List<PacienteExample> listaUsuario){

        this.usuarios=listaUsuario;

    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item,parent,false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {

        holder.txtCiudad.setText(""+usuarios.get(position).getCity());
        holder.txtDescripcion.setText(""+usuarios.get(position).getDescription());
        holder.txtLatLon.setText(""+usuarios.get(position).getLatitude()+": "+usuarios.get(position).getLongitude());


    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }



    public class UsuarioHolder extends RecyclerView.ViewHolder {

        TextView txtLatLon,txtCiudad,txtDescripcion;


        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);

            txtDescripcion=itemView.findViewById(R.id.txtUsuario);
            txtCiudad=itemView.findViewById(R.id.txtNombre);
            txtLatLon=itemView.findViewById(R.id.txtEdad);


        }
    }
}

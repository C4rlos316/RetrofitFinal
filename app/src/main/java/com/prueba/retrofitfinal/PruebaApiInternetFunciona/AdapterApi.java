package com.prueba.retrofitfinal.PruebaApiInternetFunciona;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.retrofitfinal.PruebaApiInternetFunciona.Model.Post;
import com.prueba.retrofitfinal.R;

import java.util.List;

public class AdapterApi extends RecyclerView.Adapter<AdapterApi.AdapterHolder> {

    List<Post> post;


    public AdapterApi(List<Post>post){

        this.post=post;

    }


    @NonNull
    @Override
    public AdapterApi.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item,parent,false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterApi.AdapterHolder holder, int position) {


        holder.txtId.setText(""+post.get(position).getId());
        holder.txtTitulo.setText(""+post.get(position).getTitle());
        holder.txtBody.setText(""+post.get(position).getBody());



    }

    @Override
    public int getItemCount() {
        return post.size();
    }


    public class AdapterHolder extends RecyclerView.ViewHolder{

        TextView txtId,txtTitulo,txtBody;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo=itemView.findViewById(R.id.txtUsuario);
            txtId=itemView.findViewById(R.id.txtNombre);
            txtBody=itemView.findViewById(R.id.txtEdad);

        }
    }
}

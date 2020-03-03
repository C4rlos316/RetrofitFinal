package com.prueba.retrofitfinal.Prueba2Recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.prueba.retrofitfinal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumirApi extends AppCompatActivity {

    AdapterUsuario adapterUsuario;
    List<PacienteExample> pacienteExamples=new ArrayList<>();
    RecyclerView recyclerView;

    public static PacienteService apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumir_api);

        apiInterface= ApiClient.getApiClient().create(PacienteService.class);


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llamarServicio();


    }


    public void llamarServicio(){


        Call<List<PacienteExample>> lista= apiInterface.getCity();

        lista.enqueue(new Callback<List<PacienteExample>>() {
            @Override
            public void onResponse(Call<List<PacienteExample>> call, Response<List<PacienteExample>> response) {

                //si todo sale bien isSuccefull

                if (response.isSuccessful()) {
                    pacienteExamples = response.body();
                    adapterUsuario=new AdapterUsuario(pacienteExamples);
                    recyclerView.setAdapter(adapterUsuario);
                    adapterUsuario.notifyDataSetChanged();
                    Log.e("Bien",response.message());

                }

            }

            @Override
            public void onFailure(Call<List<PacienteExample>> call, Throwable t) {

                Log.e("Mal",t.getMessage());


            }
        });



    }
}

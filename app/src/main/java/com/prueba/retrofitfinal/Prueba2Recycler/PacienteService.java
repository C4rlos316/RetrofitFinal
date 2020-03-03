package com.prueba.retrofitfinal.Prueba2Recycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PacienteService {

    @GET("ejemploRetro/Pacientes.json")
    Call<List<PacienteExample>> getCity();

}

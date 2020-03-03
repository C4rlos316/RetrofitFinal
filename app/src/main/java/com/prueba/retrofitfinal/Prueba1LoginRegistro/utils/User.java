package com.prueba.retrofitfinal.Prueba1LoginRegistro.utils;

import com.google.gson.annotations.SerializedName;

public class User {


    //Esto viene en el php en la parte de json_encode
    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;


    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}

package com.prueba.retrofitfinal.PruebaApiInternetFunciona.Interfaces;

import com.prueba.retrofitfinal.PruebaApiInternetFunciona.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlacesHolderApi {


    @GET("posts")
    Call<List<Post>> getPost();

}

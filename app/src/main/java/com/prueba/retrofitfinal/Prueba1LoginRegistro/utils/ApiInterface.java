package com.prueba.retrofitfinal.Prueba1LoginRegistro.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("register.php")
    Call<User> performRegistration(@Query("name")String name,
                                   @Query("user_name") String userName,
                                   @Query("user_password")String userPassword);

    @GET("login.php")
    Call<User> performUserLogin( @Query("user_name") String userName,
                                 @Query("user_password")String userPassword);


}

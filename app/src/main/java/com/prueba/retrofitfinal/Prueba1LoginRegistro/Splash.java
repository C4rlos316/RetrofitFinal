package com.prueba.retrofitfinal.Prueba1LoginRegistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.prueba.retrofitfinal.R;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiClient;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiInterface;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.PrefConfig;

public class Splash extends AppCompatActivity {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash);

       prefConfig=new PrefConfig(this);

       //retrofit
       apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

       if (findViewById(R.id.frameLayout)!=null){

           if (savedInstanceState!=null){

               return;
           }

           if (prefConfig.readLoginStatus()){

               Intent intent=new Intent(Splash.this, Welcome.class);
               startActivity(intent);

           }
           else {
               Intent intent=new Intent(Splash.this, InicioSesion.class);
               startActivity(intent);

           }
       }
    }
}

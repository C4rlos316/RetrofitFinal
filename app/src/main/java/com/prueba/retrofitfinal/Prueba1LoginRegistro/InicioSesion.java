package com.prueba.retrofitfinal.Prueba1LoginRegistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prueba.retrofitfinal.R;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiClient;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiInterface;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioSesion extends AppCompatActivity {

    EditText edtUsuario,edtPass;
    Button btnIniciarSesion,btnRegistro;
    public static ApiInterface apiInterface;


    //Singleton con login listo para usarse ok por el php


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);


        edtUsuario=findViewById(R.id.edtUsuario);
        edtPass=findViewById(R.id.edtPassword);

        btnIniciarSesion=findViewById(R.id.btnLogin);
        btnRegistro=findViewById(R.id.btnRegis);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(InicioSesion.this, Registrar.class);
                startActivity(intent);


            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();


            }
        });

    }

    private void performLogin(){

        String username=edtUsuario.getText().toString().trim();
        String password=edtPass.getText().toString().trim();

        Call<User> call = apiInterface.performUserLogin(username,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok")){
                    Splash.prefConfig.writeLoginStatus(true);
                    Intent intent=new Intent(InicioSesion.this, Welcome.class);
                    Splash.prefConfig.writeName(response.body().getName());
                    startActivity(intent);


                }

                else if (response.body().getResponse().equals("failed")){

                    Splash.prefConfig.displayToast("Hibo una falla intenta de new");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


                Toast.makeText(InicioSesion.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error",t.getMessage());

            }
        });

    }

}

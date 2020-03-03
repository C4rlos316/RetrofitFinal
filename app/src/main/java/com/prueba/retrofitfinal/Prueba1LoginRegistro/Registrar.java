package com.prueba.retrofitfinal.Prueba1LoginRegistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prueba.retrofitfinal.R;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiClient;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.ApiInterface;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrar extends AppCompatActivity {


    EditText edtUsuario, edtPass, edtNombre;
    Button btnIniciarSesion, btnRegistro;
    public static ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);


        edtUsuario = findViewById(R.id.edtUsuario);
        edtPass = findViewById(R.id.edtPassword);
        edtNombre = findViewById(R.id.edtNombre);


        btnIniciarSesion = findViewById(R.id.btnInic);
        btnRegistro = findViewById(R.id.btnLogin);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                perforRegistration();
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Registrar.this, InicioSesion.class);
                startActivity(intent);

            }
        });
    }


    public void perforRegistration() {

        String name = edtNombre.getText().toString().trim();
        String user = edtUsuario.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        Call<User> call = apiInterface.performRegistration(name, user, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok")) {

                    Splash.prefConfig.displayToast("Se registro correctamente");

                } else if (response.body().getResponse().equals("exist")) {

                    Splash.prefConfig.displayToast("El usuario ya existe");

                }

                else if (response.body().getResponse().equals("error")){

                    Splash.prefConfig.displayToast("Hubo un error al registrar");


                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        edtNombre.setText("");
        edtPass.setText("");
        edtUsuario.setText("");


    }
}

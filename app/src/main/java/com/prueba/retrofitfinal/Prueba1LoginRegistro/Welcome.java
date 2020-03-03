package com.prueba.retrofitfinal.Prueba1LoginRegistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prueba.retrofitfinal.R;
import com.prueba.retrofitfinal.Prueba1LoginRegistro.utils.PrefConfig;

public class Welcome extends AppCompatActivity {

    TextView textView;
    Button btnCerrarSesion;

    PrefConfig prefConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        prefConfig=new PrefConfig(this);

        textView=findViewById(R.id.textView);
        btnCerrarSesion=findViewById(R.id.btnCerrarSesion);

        textView.setText(Splash.prefConfig.readName());

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prefConfig.writeLoginStatus(false);
                prefConfig.writeName("");

                Intent intent=new Intent(Welcome.this, InicioSesion.class);
                startActivity(intent);



            }
        });

    }
}

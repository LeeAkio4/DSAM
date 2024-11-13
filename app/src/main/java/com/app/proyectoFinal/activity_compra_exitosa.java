package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_compra_exitosa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_exitosa); // Establece el layout activity_compra_exitosa.xml
    }

    public void volveralfuturos(View view){
        Intent x=new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(x);
    }
}

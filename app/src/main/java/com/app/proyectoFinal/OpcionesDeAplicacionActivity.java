package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OpcionesDeAplicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opciones_de_aplicacion);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Cerrar(View view){
        Intent x=new Intent(this, IniciarSesionActivity.class);
        startActivity(x);
    }

    public void IrPerfil(View view){
        Intent x=new Intent(this, activity_perfil.class);
        startActivity(x);
    }

    public void IrTarjeta(View view){
        Intent x=new Intent(this, activity_tarjeta.class);
        startActivity(x);
    }

    public void IrUbicacion(View view){
        Intent x=new Intent(this, activity_ubicacion.class);
        startActivity(x);
    }

    public void IrTerminos(View view){
        Intent x=new Intent(this, activity_terminoycondicion.class);
        startActivity(x);
    }

    public void IrPedidos(View view){
        Intent x=new Intent(this, activity_pedidos.class);
        startActivity(x);
    }

    public void IrTienda(View view){
        Intent x=new Intent(this, Menu_Marcas.class);
        startActivity(x);
    }

}

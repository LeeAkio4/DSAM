package com.app.proyectoFinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OpcionesDeAplicacionActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "UsuarioPrefs";
    public int codigoUsuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opciones_de_aplicacion);

        // Obtener el código de usuario del Intent o desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        codigoUsuarioActual = getIntent().getIntExtra("codigoUsuario", sharedPreferences.getInt("codigoUsuario", -1));

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Almacenar el codigoUsuarioActual en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("codigoUsuario", codigoUsuarioActual);
        editor.apply();

    }
    public void Cerrar(View view){
        Intent x=new Intent(this, IniciarSesionActivity.class);
        startActivity(x);
        finish();
    }

    public void IrPerfil(View view) {
        Intent intent = new Intent(this, activity_perfil.class);
        intent.putExtra("codigoUsuario", codigoUsuarioActual ); // Pasamos el código del usuario a PerfilActivity
        startActivity(intent);
    }

    public void IrTarjeta(View view){
        Intent x=new Intent(this, activity_tarjeta.class);
        x.putExtra("codigoUsuario", codigoUsuarioActual);
        startActivity(x);
    }

    public void IrUbicacion(View view){
        Intent x=new Intent(this, activity_ubicacion.class);
        x.putExtra("codigoUsuario", codigoUsuarioActual);
        startActivity(x);
    }

    public void IrTerminos(View view){
        Intent x=new Intent(this, activity_terminoycondicion.class);
        x.putExtra("codigoUsuario", codigoUsuarioActual);
        startActivity(x);
    }

    public void IrPedidos(View view){
        Intent x=new Intent(this, activity_pedidos.class);
        x.putExtra("codigoUsuario", codigoUsuarioActual);
        startActivity(x);
    }

    public void IrTienda(View view){
        Intent x=new Intent(this, Menu_Marcas.class);
        x.putExtra("codigoUsuario", codigoUsuarioActual);
        startActivity(x);
    }

}

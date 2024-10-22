package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu_Marcas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_marcas);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encontrar el ImageView llamado imgUser
        ImageView imgUser = findViewById(R.id.imgUser);

        // Configurar el listener para navegar a la pantalla User
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para navegar a la actividad User
                Intent intent = new Intent(Menu_Marcas.this, UserActivity.class);
                startActivity(intent);
            }
        });
        // Encontrar el botón y configurar el listener
        Button btnSiguiente = findViewById(R.id.btnRetroceder2);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para navegar a OtraActivity
                Intent intent = new Intent(Menu_Marcas.this, OpcionesDeAplicacionActivity.class);
                startActivity(intent);
            }
        });
    }
}


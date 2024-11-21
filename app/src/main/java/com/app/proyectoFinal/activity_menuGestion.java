package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_menuGestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_gestion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void atras45(View view){
        Intent x = new Intent(this, activity_MenuAdmin.class);
        startActivity(x);
        finish();
    }

    public void IrCrudProd(View view){
        Intent x = new Intent(this, activity_CrudProductos.class);
        startActivity(x);
        finish();
    }

    public void IrCrudUsua(View view){
        Intent x = new Intent(this, activity_CrudUsuarios.class);
        startActivity(x);
        finish();
    }


}
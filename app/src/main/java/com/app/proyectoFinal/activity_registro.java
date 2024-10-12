package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.negocio.nUsuario;

public class activity_registro extends AppCompatActivity {

    EditText cod, nom, ape, cel, corr, contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nom=findViewById(R.id.txtNombre);
        ape=findViewById(R.id.txtApellido);
        cel=findViewById(R.id.txtCelular);
        corr=findViewById(R.id.txtCorreo);
        contra=findViewById(R.id.logTxtclave);
    }

    public void Regresar(View view){
        Intent x=new Intent(this, IniciarSesionActivity.class);
        startActivity(x);
    }

    public void insertar(View view){
        nUsuario x=new nUsuario(this);
        x.insertar(
                Integer.parseInt(cod.getText().toString()),
                nom.getText().toString(),
                ape.getText().toString(),
                cel.getText().toString(),
                corr.getText().toString(),
                contra.getText().toString()
        );
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Dato registrado").setTitle("Registro");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        Toast.makeText(this, "Dato registrado", Toast.LENGTH_LONG).show();
    }
    public void inicio(View view){
        finish();
    }
}
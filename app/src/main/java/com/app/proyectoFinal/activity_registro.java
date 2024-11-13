package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.dao.conexion;

public class activity_registro extends AppCompatActivity {

    EditText cod, nomcom, direc, dni, gener, corr, contra, contraconf;
    Button btnRegistrar;
    conexion dbHelper;

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

        nomcom=findViewById(R.id.txtNombrecompleto);
        direc=findViewById(R.id.txtDireccion);
        dni=findViewById(R.id.txtDni);
        gener=findViewById(R.id.txtGenero);
        corr=findViewById(R.id.txtCorreo);
        contra=findViewById(R.id.logTxtclave);
        contraconf=findViewById(R.id.txtConfirmarClave);
        btnRegistrar = findViewById(R.id.btnConfirmar);
        dbHelper = new conexion(this);

        // Establecer el OnClickListener
        btnRegistrar.setOnClickListener(v -> {
            String nombreCompleto = nomcom.getText().toString().trim();
            String direccion = direc.getText().toString().trim();
            String dniTexto = dni.getText().toString().trim();
            String genero = gener.getText().toString().trim();
            String correo = corr.getText().toString().trim();
            String contrasena = contra.getText().toString().trim();
            String contrasenaConfirmar = contraconf.getText().toString().trim(); // Nueva línea

            if (!nombreCompleto.isEmpty() && !direccion.isEmpty() && !dniTexto.isEmpty() &&
                    !genero.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty() &&
                    !contrasenaConfirmar.isEmpty()) { // Nueva verificación

                if (contrasena.equals(contrasenaConfirmar)) { // Verificar que las contraseñas coinciden
                    boolean registroExitoso = dbHelper.registrarse(nombreCompleto, direccion, dniTexto, genero, correo, contrasena);

                    if (registroExitoso) {
                        Toast.makeText(activity_registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        // Redirigir a otra actividad después del registro
                        Intent intent = new Intent(activity_registro.this, IniciarSesionActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(activity_registro.this, "El correo ya está registrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity_registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show(); // Mensaje de error
                }
            } else {
                Toast.makeText(activity_registro.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Regresar(View view){
        Intent x=new Intent(this, IniciarSesionActivity.class);
        startActivity(x);
    }

}
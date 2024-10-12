package com.app.proyectoFinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.dao.conexion;
import com.google.android.material.textfield.TextInputEditText;

public class IniciarSesionActivity extends AppCompatActivity {

    // Definir las variables para los campos de correo y contraseña
    TextInputEditText correo;
    TextInputEditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciar_sesion);

        // Inicializar los campos de correo y contraseña
        correo = findViewById(R.id.IdCorreo);
        contrasena = findViewById(R.id.IdContraseña);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el botón para regresar a MainActivity
        Button btnRegresar = findViewById(R.id.btnsalir);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la MainActivity
                Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual para que no se mantenga en el back stack
            }
        });

        // Configurar el botón para avanzar si la validación es correcta
        Button btnSiguiente = findViewById(R.id.btniniciarsesion);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados en los campos de correo y contraseña
                String correoIngresado = correo.getText().toString();
                String contrasenaIngresada = contrasena.getText().toString();

                // Verificar si el correo y la contraseña son correctos (admin/admin)
                if (correoIngresado.equals("admin") && contrasenaIngresada.equals("admin")) {
                    // Crear un intent para navegar al layout de Menu Marcas
                    Intent intent = new Intent(IniciarSesionActivity.this, OpcionesDeAplicacionActivity.class);
                    startActivity(intent);
                } else {
                    // Mostrar un mensaje de error si el correo o la contraseña son incorrectos
                    Toast.makeText(IniciarSesionActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void IrRegistro(View view){
        Intent x=new Intent(this, activity_registro.class);
        startActivity(x);
    }

    public void CrearDB(View view){
        conexion con= new conexion(this);
        SQLiteDatabase database= con.getWritableDatabase();
        if (database!=null){
            Toast.makeText(this, "Conexion correcta", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
        }
    }
    public void envioInicio(View view){
        finish();
    }
}

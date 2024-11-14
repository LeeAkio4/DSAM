package com.app.proyectoFinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.app.proyectoFinal.controlador.cUsuario;
import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class IniciarSesionActivity extends AppCompatActivity {

    // Definir las variables para los campos de correo y contraseña
    EditText etcorreo, etcontrasena;
    Button btnLogin;
    conexion dbConexion;
    cUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controladorUsuario = new cUsuario(this);
        CrearDB();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciar_sesion);

        // Inicializar los campos de correo y contraseña
        etcorreo = findViewById(R.id.IdCorreo);
        etcontrasena = findViewById(R.id.IdContraseña);
        btnLogin = findViewById(R.id.btnLogin);
        dbConexion = new conexion(this);


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

        btnLogin.setOnClickListener(v -> {
            String correo = etcorreo.getText().toString();
            String contrasena = etcontrasena.getText().toString();

            // Verificar si el usuario es administrador
            if (correo.equals("admin") && contrasena.equals("admin")) {
                Toast.makeText(IniciarSesionActivity.this, "Bienvenido, Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IniciarSesionActivity.this, activity_MenuAdmin.class); // Redirige al menú de administrador
                startActivity(intent);
            } else {
                // Continuar con el flujo normal de inicio de sesión
                Usuario usuario = controladorUsuario.ValidarUsuario(correo, contrasena); // Cambia a este método

                if (dbConexion.verificarLogin(correo, contrasena)) {
                    Toast.makeText(IniciarSesionActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IniciarSesionActivity.this, OpcionesDeAplicacionActivity.class);
                    intent.putExtra("codigoUsuario", usuario.getCodigo()); // Pasamos el código del usuario
                    UsuarioManager.getInstance().setCodigoUsuario(usuario.getCodigo());
                    startActivity(intent);
                } else {
                    Toast.makeText(IniciarSesionActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void IrRegistro(View view){
        Intent x=new Intent(this, activity_registro.class);
        startActivity(x);
    }

    public void CrearDB(){
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

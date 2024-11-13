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

public class activity_editar_perfil extends AppCompatActivity {

    private EditText txtNombrecompleto, txtDireccion, txtGenero, txtCorreo, logTxtclave, logTxtconfclave;
    private int codigoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

        // Obtener código de usuario del intent
        codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);

        // Inicializar vistas
        txtNombrecompleto = findViewById(R.id.txtNombrecompleto);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtGenero = findViewById(R.id.txtGenero);
        txtCorreo = findViewById(R.id.txtCorreo);
        logTxtclave = findViewById(R.id.logTxtclave);
        logTxtconfclave = findViewById(R.id.logTxtconfclave);

        // Configurar el botón de confirmar
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrescompletos = txtNombrecompleto.getText().toString();
                String direccion = txtDireccion.getText().toString();
                String genero = txtGenero.getText().toString();
                String correo = txtCorreo.getText().toString();
                String contrasena = logTxtclave.getText().toString();
                String confContrasena = logTxtconfclave.getText().toString();

                // Verificar que las contraseñas coincidan
                if (!contrasena.equals(confContrasena)) {
                    Toast.makeText(activity_editar_perfil.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Actualizar datos del usuario en la base de datos
                conexion dbConexion = new conexion(activity_editar_perfil.this);
                boolean actualizado = dbConexion.actualizarUsuario(codigoUsuario, nombrescompletos, direccion, genero, correo, contrasena);

                if (actualizado) {
                    Toast.makeText(activity_editar_perfil.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity_editar_perfil.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Cancelar(View view) {
        Intent x = new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(x);
    }
}

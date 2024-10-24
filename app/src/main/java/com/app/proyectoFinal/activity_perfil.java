package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.controlador.cUsuario;
import com.app.proyectoFinal.modelo.Usuario;

public class activity_perfil extends AppCompatActivity {

    cUsuario controladorUsuario;
    TextView nombreTextView, generoTextView, direccionTextView, dniTextView, idsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controladorUsuario = new cUsuario(this);
        nombreTextView = findViewById(R.id.txtNombres);
        generoTextView = findViewById(R.id.txtgenerotext);
        direccionTextView = findViewById(R.id.txtdirtext);
        dniTextView = findViewById(R.id.txtnrodni);
        idsTextView = findViewById(R.id.txtIdP);


        int codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);
        cargarDatosUsuario(codigoUsuario);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cargar los datos del usuario nuevamente al reanudar la actividad
        int codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);
        cargarDatosUsuario(codigoUsuario);
    }

    private void cargarDatosUsuario(int codigoUsuario) {
        if (codigoUsuario != -1) {
            Usuario usuario = controladorUsuario.getUsuarioPorCodigo(codigoUsuario);

            // Agregar mensaje de depuración
            Toast.makeText(this, "Cargando usuario con código: " + codigoUsuario, Toast.LENGTH_SHORT).show();

            if (usuario != null) {
                nombreTextView.setText(usuario.getNombrescompletos());
                generoTextView.setText(usuario.getGenero());
                direccionTextView.setText(usuario.getDireccion());
                dniTextView.setText(String.valueOf(usuario.getDni()));
                idsTextView.setText("ID: N00" + codigoUsuario);
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Código de usuario no válido", Toast.LENGTH_SHORT).show();
        }
    }
    public void Actualizar(View view){
        Intent x=new Intent(this, activity_editar_perfil.class);
        startActivity(x);
    }

    public void Regresar(View view){
        finish();
    }
}





package com.app.proyectoFinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.List;

public class activity_CrudUsuarios extends AppCompatActivity {

    private ImageView btnMostrarModal;
    private EditText txusuarios;
    private conexion conexionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud_usuarios);


        btnMostrarModal = findViewById(R.id.btnInsertarUsuario);
        txusuarios = findViewById(R.id.txtmultilineUsuarios);

        conexionDB = new conexion(this);
        btnMostrarModal.setOnClickListener(v -> mostrarModalAgregarUsuario());
        mostrarUsuarios();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = conexionDB.obtenerTodosLosUsuarios();

        if (usuarios.isEmpty()) {
            txusuarios.setText("No hay usuarios disponibles.");
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

            for (Usuario usuario : usuarios) {
                String textoUsuario = "Código: " + usuario.getCodigo() + "\n" +
                        "Nombre Completo: " + usuario.getNombrescompletos() + "\n" +
                        "Dirección: " + usuario.getDireccion() + "\n" +
                        "DNI: " + usuario.getDni() + "\n" +
                        "Género: " + usuario.getGenero() + "\n" +
                        "Correo: " + usuario.getCorreo() + "\n\n";

                int start = spannableStringBuilder.length();
                spannableStringBuilder.append(textoUsuario);
                int end = spannableStringBuilder.length();

                spannableStringBuilder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        mostrarModalUsuarioSeleccionado(usuario);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLACK);
                        ds.setUnderlineText(false);
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            txusuarios.setText(spannableStringBuilder);
            txusuarios.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void mostrarModalUsuarioSeleccionado(Usuario usuario) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_modificar_usuario, null);

        EditText edtNombre = view.findViewById(R.id.edtNombreCompleto);
        EditText edtDireccion = view.findViewById(R.id.edtDireccion);
        EditText edtDni = view.findViewById(R.id.edtDni);
        EditText edtGenero = view.findViewById(R.id.edtGenero);
        EditText edtCorreo = view.findViewById(R.id.edtCorreo);
        EditText edtContrasena = view.findViewById(R.id.edtContrasena);

        edtNombre.setText(usuario.getNombrescompletos());
        edtDireccion.setText(usuario.getDireccion());
        edtDni.setText(String.valueOf(usuario.getDni()));
        edtGenero.setText(usuario.getGenero());
        edtCorreo.setText(usuario.getCorreo());
        edtContrasena.setText(usuario.getContrasena());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalle de Usuario")
                .setView(view)
                .setPositiveButton("Modificar", (dialog, which) -> {
                    usuario.setNombrescompletos(edtNombre.getText().toString());
                    usuario.setDireccion(edtDireccion.getText().toString());
                    usuario.setDni(Integer.parseInt(edtDni.getText().toString()));
                    usuario.setGenero(edtGenero.getText().toString());
                    usuario.setCorreo(edtCorreo.getText().toString());
                    usuario.setContrasena(edtContrasena.getText().toString());

                    conexionDB.actualizarUsuario(usuario);
                    mostrarUsuarios();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .setNeutralButton("Eliminar", (dialog, which) -> {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Estás seguro de que deseas eliminar este usuario?")
                            .setPositiveButton("Eliminar", (dialog1, which1) -> {
                                conexionDB.eliminarUsuario(usuario.getCodigo());
                                mostrarUsuarios();
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
                })
                .show();
    }

    private void mostrarModalAgregarUsuario() {
        View modalView = getLayoutInflater().inflate(R.layout.activity_insertar_usuario, null);

        EditText edtNombre = modalView.findViewById(R.id.edtNombreCompleto);
        EditText edtDireccion = modalView.findViewById(R.id.edtDireccion);
        EditText edtDni = modalView.findViewById(R.id.edtDni);
        EditText edtGenero = modalView.findViewById(R.id.edtGenero);
        EditText edtCorreo = modalView.findViewById(R.id.edtCorreo);
        EditText edtContrasena = modalView.findViewById(R.id.edtContrasena);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Usuario")
                .setView(modalView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombrescompletos(edtNombre.getText().toString());
                    nuevoUsuario.setDireccion(edtDireccion.getText().toString());
                    nuevoUsuario.setDni(Integer.parseInt(edtDni.getText().toString()));
                    nuevoUsuario.setGenero(edtGenero.getText().toString());
                    nuevoUsuario.setCorreo(edtCorreo.getText().toString());
                    nuevoUsuario.setContrasena(edtContrasena.getText().toString());

                    long idUsuario = conexionDB.agregarUsuario(nuevoUsuario);

                    if (idUsuario != -1) {
                        Toast.makeText(this, "Usuario añadido exitosamente", Toast.LENGTH_SHORT).show();
                        mostrarUsuarios();
                    } else {
                        Toast.makeText(this, "Error al añadir el usuario", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    // Acción para volver a la pantalla anterior
    public void atras4(View view){
        Intent x = new Intent(this, activity_menuGestion.class);
        startActivity(x);
        finish();
    }
}
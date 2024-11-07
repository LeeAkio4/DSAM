package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.modelo.Tarjeta;

public class activity_añadir_tarjeta extends AppCompatActivity {
    EditText nombreEditText, correoEditText, numTarjetaEditText, fechaCaducidadEditText, cvvEditText;
    int codigoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_tarjeta);
        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia a los elementos de entrada en el formulario
        nombreEditText = findViewById(R.id.Nombre);
        correoEditText = findViewById(R.id.logTxtCorreo);
        numTarjetaEditText = findViewById(R.id.logTxtNumTarj);
        fechaCaducidadEditText = findViewById(R.id.logFechaTajetaDeCaducidad);
        cvvEditText = findViewById(R.id.logCVV);

        // Obtener el código del usuario desde el Intent
        codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1); // Recibir como entero
        if (codigoUsuario == -1) {
            Toast.makeText(this, "Error: usuario no autenticado", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad si no hay un usuario logueado
        }
    }

    public void Regresar(View view){
        Intent x=new Intent(this, activity_tarjeta.class);
        startActivity(x);
    }

    public void registrarTarjeta(View view) {
        String nombre = nombreEditText.getText().toString().trim();
        String correo = correoEditText.getText().toString().trim();
        String numTarjetaStr = numTarjetaEditText.getText().toString().trim();
        String fechaCaducidad = fechaCaducidadEditText.getText().toString().trim();
        String cvv = cvvEditText.getText().toString().trim();

        // Validación de campos
        if (nombre.isEmpty() || correo.isEmpty() || numTarjetaStr.isEmpty() || fechaCaducidad.isEmpty() || cvv.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación de correo electrónico
        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Toast.makeText(this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar la longitud del número de tarjeta y CVV
        if (numTarjetaStr.length() != 16) {
            Toast.makeText(this, "El número de tarjeta debe tener 16 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cvv.length() != 3) {
            Toast.makeText(this, "El CVV debe tener 3 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir el número de tarjeta a long
        long numTarjeta;
        try {
            numTarjeta = Long.parseLong(numTarjetaStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de tarjeta no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una instancia de Tarjeta
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodigo_u(codigoUsuario); // Asegúrate que el código de usuario sea un int
        tarjeta.setNombre(nombre);
        tarjeta.setCorreo(correo);
        tarjeta.setFechacad(fechaCaducidad);
        tarjeta.setCvv(cvv);
        tarjeta.setNumeroTar(numTarjeta);

        // Guardar la tarjeta en la base de datos
        cTarjeta controladorTarjeta = new cTarjeta(this);
        try {
            controladorTarjeta.insert(tarjeta); // Intentar insertar
            Toast.makeText(this, "Tarjeta registrada con éxito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_tarjeta.class);
            startActivity(intent);
            limpiarCampos();
        } catch (RuntimeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Mostrar el mensaje de error
        }
    }

    private void limpiarCampos() {
        nombreEditText.setText("");
        correoEditText.setText("");
        numTarjetaEditText.setText("");
        fechaCaducidadEditText.setText("");
        cvvEditText.setText("");
    }
}


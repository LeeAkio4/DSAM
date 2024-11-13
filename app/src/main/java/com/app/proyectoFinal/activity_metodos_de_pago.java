package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Tarjeta;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.List;

public class activity_metodos_de_pago extends AppCompatActivity {

    List<Tarjeta> tarjetas;
    cTarjeta dbConexion;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_metodos_de_pago);

        // Obtener el codigoUsuario desde UsuarioManager
        int codigoUsuario = UsuarioManager.getInstance().getCodigoUsuario();


        if (codigoUsuario == -1) {
            Toast.makeText(this, "No hay un usuario iniciado", Toast.LENGTH_SHORT).show();
            return; // Si no hay un usuario logueado, salimos de la actividad o mostramos un mensaje
        }

        // Inicializar la conexión a la base de datos
        dbConexion = new cTarjeta(this);

        // Obtener las tarjetas del usuario
        tarjetas = dbConexion.obtenerTarjetasPorUsuario(codigoUsuario);

        Button btnRealizarCompras = findViewById(R.id.btnRealizarCompra);

        // Verificar si el usuario tiene tarjetas
        if (tarjetas.isEmpty()) {
            Toast.makeText(this, "No tienes tarjetas asociadas", Toast.LENGTH_SHORT).show();
            btnRealizarCompras.setEnabled(false);
        } else {
            // Crear el ArrayAdapter para el Spinner
            String[] tarjetasStrings = new String[tarjetas.size()];
            for (int i = 0; i < tarjetas.size(); i++) {
                Tarjeta tarjeta = tarjetas.get(i);
                tarjetasStrings[i] = tarjeta.getNombre() + " - " + tarjeta.getNumeroTar() + " - " + tarjeta.getFechacad();  // Mostrar nombre, numero de tarjeta y fecha de caducidad
            }

            // Crear un ArrayAdapter para el Spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tarjetasStrings);

            // Obtener el Spinner desde el layout
            Spinner spinnerMetodoPago = findViewById(R.id.txtelegirtarjetas);

            // Asignar el adaptador al Spinner
            spinnerMetodoPago.setAdapter(adapter);

            // Configurar el listener para manejar la selección
            spinnerMetodoPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Obtener la tarjeta seleccionada
                    Tarjeta tarjetaSeleccionada = tarjetas.get(position);
                    // Mostrar un mensaje con la tarjeta seleccionada
                    Toast.makeText(activity_metodos_de_pago.this, "Seleccionaste: " + tarjetaSeleccionada.getNombre(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Si no se selecciona nada, puedes manejarlo aquí
                }
            });
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void volveralfuturo(View view){
        Intent x=new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(x);
    }

    public void RealizarCompra(View view){

        int codigoPedido = getIntent().getIntExtra("codigoPedido", -1);
        Log.d("CodigoPedido", "Codigo del pedido: " + codigoPedido);
        // Verificar si el código del pedido es válido
        if (codigoPedido == -1) {
            Toast.makeText(this, "No se ha encontrado el pedido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Conectar a la base de datos (suponiendo que tienes un controlador para pedidos)
        cPedido dbPedido = new cPedido(this);

        // Actualizar el estado del pedido a "Pagado"
        boolean exito = dbPedido.actualizarEstadoPedido(codigoPedido, "Pagado");

        if (exito) {
            // Si el estado se actualizó correctamente, redirigir a la actividad de compra exitosa
            Intent intent = new Intent(this, activity_compra_exitosa.class);
            startActivity(intent);
        } else {
            // Si hubo algún problema al actualizar el pedido, mostrar un mensaje
            Toast.makeText(this, "Error al actualizar el estado del pedido", Toast.LENGTH_SHORT).show();
        }
    }

}

package com.app.proyectoFinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class activity_CrudProductos extends AppCompatActivity {

    private ImageView btnMostrarModal;
    private EditText txproductos;
    private conexion conexionDB;
    private List<Producto> productosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_productos);

        // Inicializar vistas para lo del modal, el image button es un ImageView
        btnMostrarModal = findViewById(R.id.btnInsertarProd);
        txproductos = findViewById(R.id.txtmultiline);
        productosList = new ArrayList<>();
        // Inicializar la conexión con la base de datos
        conexionDB = new conexion(this);
        // Manejar el click del botón "Mostrar Modal"
        btnMostrarModal.setOnClickListener(v -> mostrarModalAgregarProducto());
        mostrarProductos();
    }

    private void mostrarProductos() {
        // Obtener todos los productos de la base de datos
        List<Producto> productos = conexionDB.obtenerTodosLosProductos();

        // Verificar si hay productos
        if (productos.isEmpty()) {
            txproductos.setText("No hay productos disponibles.");
        } else {
            // Crear un StringBuilder para almacenar todos los productos
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

            for (Producto producto : productos) {
                // Crear el texto completo para mostrar
                String textoProducto = "Codigo: " + producto.getCodigo_prod() + "\n" +
                        "Nombre: " + producto.getNombre() + "\n" +
                        "Marca: " + producto.getMarca() + "\n" +
                        "Descripción: " + producto.getDescripcion() + "\n" +
                        "Precio: $" + producto.getPrecio() + "\n" +
                        "Año: " + producto.getAnio() + "\n" +
                        "Color: " + producto.getColor() + "\n" +
                        "Cilindros: " + producto.getCilindros() + "\n" +
                        "Transmisión: " + producto.getTransmision() + "\n" +
                        "Tipo de Motor: " + producto.getTipomotor() + "\n" +
                        "Placa: " + producto.getPlaca() + "\n" +
                        "Stock: " + producto.getStock() + "\n\n";

                // Agregar este texto al SpannableStringBuilder
                int start = spannableStringBuilder.length();
                spannableStringBuilder.append(textoProducto);
                int end = spannableStringBuilder.length();

                // Hacer todo el bloque de texto clickeable y personalizar su estilo
                spannableStringBuilder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        // Cuando se hace clic, abrir el modal con los datos del producto
                        mostrarModalProductoSeleccionado(producto);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLACK);  // Cambiar el color del texto
                        ds.setUnderlineText(false);  // Eliminar el subrayado
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // Establecer el texto en el TextView
            txproductos.setText(spannableStringBuilder);
            txproductos.setMovementMethod(LinkMovementMethod.getInstance());  // Necesario para que los enlaces sean clickeables
        }
    }

    private void mostrarModalProductoSeleccionado(Producto producto) {
        // Inflar el layout personalizado para el modal
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_modificar_producto, null);

        // Referenciar los EditTexts en el layout personalizado
        EditText edtNombre = view.findViewById(R.id.edtNombre);
        EditText edtMarca = view.findViewById(R.id.edtMarca);
        EditText edtDescripcion = view.findViewById(R.id.edtDescripcion);
        EditText edtPrecio = view.findViewById(R.id.edtPrecio);
        EditText edtAnio = view.findViewById(R.id.edtAnio);
        EditText edtColor = view.findViewById(R.id.edtColor);
        EditText edtCilindros = view.findViewById(R.id.edtCilindros);
        EditText edtTransmision = view.findViewById(R.id.edtTransmision);
        EditText edtTipoMotor = view.findViewById(R.id.edtTipoMotor);
        EditText edtPlaca = view.findViewById(R.id.edtPlaca);
        EditText edtStock = view.findViewById(R.id.edtStock);

        // Rellenar los campos con los datos del producto seleccionado

        edtNombre.setText(producto.getNombre());
        edtMarca.setText(producto.getMarca());
        edtDescripcion.setText(producto.getDescripcion());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));
        edtAnio.setText(String.valueOf(producto.getAnio()));
        edtColor.setText(producto.getColor());
        edtCilindros.setText(String.valueOf(producto.getCilindros()));
        edtTransmision.setText(producto.getTransmision());
        edtTipoMotor.setText(producto.getTipomotor());
        edtPlaca.setText(producto.getPlaca());
        edtStock.setText(String.valueOf(producto.getStock()));

        // Crear el modal de diálogo con el layout inflado
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalle de Producto")
                .setView(view)
                .setPositiveButton("Modificar", (dialog, which) -> {
                    // Al hacer clic en "Modificar", actualizar los datos del producto

                    producto.setNombre(edtNombre.getText().toString());
                    producto.setMarca(edtMarca.getText().toString());
                    producto.setDescripcion(edtDescripcion.getText().toString());
                    producto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                    producto.setAnio(Integer.parseInt(edtAnio.getText().toString()));
                    producto.setColor(edtColor.getText().toString());
                    producto.setCilindros(Integer.parseInt(edtCilindros.getText().toString()));
                    producto.setTransmision(edtTransmision.getText().toString());
                    producto.setTipomotor(edtTipoMotor.getText().toString());
                    producto.setPlaca(edtPlaca.getText().toString());
                    producto.setStock(Integer.parseInt(edtStock.getText().toString()));

                    Log.d("ActualizarProducto", "Código del producto antes de la actualización: " + producto.getCodigo_prod());
                    conexionDB.actualizarProducto(producto);
                    mostrarProductos();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss()) // Cerrar el modal sin hacer nada

                // Botón para eliminar el producto
                .setNeutralButton("Eliminar", (dialog, which) -> {
                    // Confirmar eliminación
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Estás seguro de que deseas eliminar este producto?")
                            .setPositiveButton("Eliminar", (dialog1, which1) -> {
                                conexionDB.eliminarProducto(producto.getCodigo_prod()); // Llamada a la función de eliminación
                                mostrarProductos(); // Refresca la lista de productos
                            })
                            .setNegativeButton("Cancelar", null) // Cierra el diálogo de confirmación
                            .show();
                })
                .show();
    }

    private void mostrarModalAgregarProducto() {
        // Inflar el layout del modal
        View modalView = getLayoutInflater().inflate(R.layout.activity_insertar_producto, null);

        // Obtener las referencias de los campos del modal
        EditText edtNombre = modalView.findViewById(R.id.edtNombre);
        EditText edtMarca = modalView.findViewById(R.id.edtMarca);
        EditText edtDescripcion = modalView.findViewById(R.id.edtDescripcion);
        EditText edtPrecio = modalView.findViewById(R.id.edtPrecio);
        EditText edtAnio = modalView.findViewById(R.id.edtAnio);
        EditText edtColor = modalView.findViewById(R.id.edtColor);
        EditText edtCilindros = modalView.findViewById(R.id.edtCilindros);
        EditText edtTransmision = modalView.findViewById(R.id.edtTransmision);
        EditText edtTipoMotor = modalView.findViewById(R.id.edtTipoMotor);
        EditText edtPlaca = modalView.findViewById(R.id.edtPlaca);
        EditText edtStock = modalView.findViewById(R.id.edtStock);

        // Crear el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Producto")
                .setView(modalView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    // Crear un objeto Producto con los datos del modal
                    Producto nuevoProducto = new Producto();
                    nuevoProducto.setNombre(edtNombre.getText().toString());
                    nuevoProducto.setMarca(edtMarca.getText().toString());
                    nuevoProducto.setDescripcion(edtDescripcion.getText().toString());
                    nuevoProducto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                    nuevoProducto.setAnio(Integer.parseInt(edtAnio.getText().toString()));
                    nuevoProducto.setColor(edtColor.getText().toString());
                    nuevoProducto.setCilindros(Integer.parseInt(edtCilindros.getText().toString()));
                    nuevoProducto.setTransmision(edtTransmision.getText().toString());
                    nuevoProducto.setTipomotor(edtTipoMotor.getText().toString());
                    nuevoProducto.setPlaca(edtPlaca.getText().toString());
                    nuevoProducto.setStock(Integer.parseInt(edtStock.getText().toString()));

                    // Llamar a la función para añadir el producto a la base de datos
                    long idProducto = conexionDB.agregarProducto(nuevoProducto);

                    if (idProducto != -1) {
                        Toast.makeText(this, "Producto añadido exitosamente", Toast.LENGTH_SHORT).show();
                        mostrarProductos();
                    } else {
                        Toast.makeText(this, "Error al añadir el producto", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();

    }

    // Acción para volver a la pantalla anterior
    public void atras4(View view){
        Intent x = new Intent(this, activity_MenuAdmin.class);
        startActivity(x);
        finish();
    }
}
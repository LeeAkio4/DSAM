package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class activity_ubicacion extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener{

    EditText txtLat, txtLon;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuestra_ubicacion);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //txtLat=findViewById(R.id.txtlatitud);
        //txtLon=findViewById(R.id.txtlongitud);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLat.setText(""+latLng.latitude);
        txtLon.setText(""+latLng.longitude);

        LatLng sitio= latLng; //new LatLng(latLng.latitude,)
        mMap.addMarker(new MarkerOptions().position(sitio).title("x1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sitio, 18f));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLat.setText(""+latLng.latitude);
        txtLon.setText(""+latLng.longitude);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap=googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng sitio = new LatLng(-12.179276629148797, -77.0036432147026);
        mMap.addMarker(new MarkerOptions().position(sitio).title("Autonex"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sitio, 16.75f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    public void Regresar(View view){
        Intent x=new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(x);
    }



}
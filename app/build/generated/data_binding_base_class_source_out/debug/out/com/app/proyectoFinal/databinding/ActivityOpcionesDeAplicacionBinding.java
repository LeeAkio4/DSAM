// Generated by view binder compiler. Do not edit!
package com.app.proyectoFinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.proyectoFinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOpcionesDeAplicacionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonAtras;

  @NonNull
  public final ImageView flecha1;

  @NonNull
  public final ImageView flecha2;

  @NonNull
  public final ImageView flecha3;

  @NonNull
  public final ImageView flecha4;

  @NonNull
  public final ImageView flecha5;

  @NonNull
  public final ImageView flecha6;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout m;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView miperfil;

  @NonNull
  public final TextView txtTeryCon;

  @NonNull
  public final TextView txtTienda;

  @NonNull
  public final TextView txtubi;

  @NonNull
  public final LinearLayout vacio;

  private ActivityOpcionesDeAplicacionBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonAtras, @NonNull ImageView flecha1, @NonNull ImageView flecha2,
      @NonNull ImageView flecha3, @NonNull ImageView flecha4, @NonNull ImageView flecha5,
      @NonNull ImageView flecha6, @NonNull LinearLayout linearLayout, @NonNull LinearLayout m,
      @NonNull ConstraintLayout main, @NonNull TextView miperfil, @NonNull TextView txtTeryCon,
      @NonNull TextView txtTienda, @NonNull TextView txtubi, @NonNull LinearLayout vacio) {
    this.rootView = rootView;
    this.buttonAtras = buttonAtras;
    this.flecha1 = flecha1;
    this.flecha2 = flecha2;
    this.flecha3 = flecha3;
    this.flecha4 = flecha4;
    this.flecha5 = flecha5;
    this.flecha6 = flecha6;
    this.linearLayout = linearLayout;
    this.m = m;
    this.main = main;
    this.miperfil = miperfil;
    this.txtTeryCon = txtTeryCon;
    this.txtTienda = txtTienda;
    this.txtubi = txtubi;
    this.vacio = vacio;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOpcionesDeAplicacionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOpcionesDeAplicacionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_opciones_de_aplicacion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOpcionesDeAplicacionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonAtras;
      Button buttonAtras = ViewBindings.findChildViewById(rootView, id);
      if (buttonAtras == null) {
        break missingId;
      }

      id = R.id.flecha1;
      ImageView flecha1 = ViewBindings.findChildViewById(rootView, id);
      if (flecha1 == null) {
        break missingId;
      }

      id = R.id.flecha2;
      ImageView flecha2 = ViewBindings.findChildViewById(rootView, id);
      if (flecha2 == null) {
        break missingId;
      }

      id = R.id.flecha3;
      ImageView flecha3 = ViewBindings.findChildViewById(rootView, id);
      if (flecha3 == null) {
        break missingId;
      }

      id = R.id.flecha4;
      ImageView flecha4 = ViewBindings.findChildViewById(rootView, id);
      if (flecha4 == null) {
        break missingId;
      }

      id = R.id.flecha5;
      ImageView flecha5 = ViewBindings.findChildViewById(rootView, id);
      if (flecha5 == null) {
        break missingId;
      }

      id = R.id.flecha6;
      ImageView flecha6 = ViewBindings.findChildViewById(rootView, id);
      if (flecha6 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.m;
      LinearLayout m = ViewBindings.findChildViewById(rootView, id);
      if (m == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.miperfil;
      TextView miperfil = ViewBindings.findChildViewById(rootView, id);
      if (miperfil == null) {
        break missingId;
      }

      id = R.id.txtTeryCon;
      TextView txtTeryCon = ViewBindings.findChildViewById(rootView, id);
      if (txtTeryCon == null) {
        break missingId;
      }

      id = R.id.txtTienda;
      TextView txtTienda = ViewBindings.findChildViewById(rootView, id);
      if (txtTienda == null) {
        break missingId;
      }

      id = R.id.txtubi;
      TextView txtubi = ViewBindings.findChildViewById(rootView, id);
      if (txtubi == null) {
        break missingId;
      }

      id = R.id.vacio;
      LinearLayout vacio = ViewBindings.findChildViewById(rootView, id);
      if (vacio == null) {
        break missingId;
      }

      return new ActivityOpcionesDeAplicacionBinding((ConstraintLayout) rootView, buttonAtras,
          flecha1, flecha2, flecha3, flecha4, flecha5, flecha6, linearLayout, m, main, miperfil,
          txtTeryCon, txtTienda, txtubi, vacio);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

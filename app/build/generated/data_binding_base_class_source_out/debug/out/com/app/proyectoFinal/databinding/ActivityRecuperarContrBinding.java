// Generated by view binder compiler. Do not edit!
package com.app.proyectoFinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.proyectoFinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRecuperarContrBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button LogBtnIniciar;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final Button logBtnSalir;

  @NonNull
  public final EditText logTxtCorreo;

  @NonNull
  public final ConstraintLayout main;

  private ActivityRecuperarContrBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button LogBtnIniciar, @NonNull LinearLayout linearLayout,
      @NonNull Button logBtnSalir, @NonNull EditText logTxtCorreo, @NonNull ConstraintLayout main) {
    this.rootView = rootView;
    this.LogBtnIniciar = LogBtnIniciar;
    this.linearLayout = linearLayout;
    this.logBtnSalir = logBtnSalir;
    this.logTxtCorreo = logTxtCorreo;
    this.main = main;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRecuperarContrBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRecuperarContrBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_recuperar_contr, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRecuperarContrBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LogBtnIniciar;
      Button LogBtnIniciar = ViewBindings.findChildViewById(rootView, id);
      if (LogBtnIniciar == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.logBtnSalir;
      Button logBtnSalir = ViewBindings.findChildViewById(rootView, id);
      if (logBtnSalir == null) {
        break missingId;
      }

      id = R.id.logTxtCorreo;
      EditText logTxtCorreo = ViewBindings.findChildViewById(rootView, id);
      if (logTxtCorreo == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      return new ActivityRecuperarContrBinding((ConstraintLayout) rootView, LogBtnIniciar,
          linearLayout, logBtnSalir, logTxtCorreo, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
// Generated by view binder compiler. Do not edit!
package com.app.proyectoFinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.proyectoFinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMisPedidosBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonRegresar;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView recyclerProductos;

  @NonNull
  public final RecyclerView recyclerViewT;

  private ActivityMisPedidosBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonRegresar, @NonNull LinearLayout linearLayout3,
      @NonNull ConstraintLayout main, @NonNull TextView recyclerProductos,
      @NonNull RecyclerView recyclerViewT) {
    this.rootView = rootView;
    this.buttonRegresar = buttonRegresar;
    this.linearLayout3 = linearLayout3;
    this.main = main;
    this.recyclerProductos = recyclerProductos;
    this.recyclerViewT = recyclerViewT;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMisPedidosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMisPedidosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_mis_pedidos, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMisPedidosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonRegresar;
      Button buttonRegresar = ViewBindings.findChildViewById(rootView, id);
      if (buttonRegresar == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.recyclerProductos;
      TextView recyclerProductos = ViewBindings.findChildViewById(rootView, id);
      if (recyclerProductos == null) {
        break missingId;
      }

      id = R.id.recyclerViewT;
      RecyclerView recyclerViewT = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewT == null) {
        break missingId;
      }

      return new ActivityMisPedidosBinding((ConstraintLayout) rootView, buttonRegresar,
          linearLayout3, main, recyclerProductos, recyclerViewT);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
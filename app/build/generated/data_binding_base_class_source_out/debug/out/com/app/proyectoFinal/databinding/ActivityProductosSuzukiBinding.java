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

public final class ActivityProductosSuzukiBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAtrasProducSuzuki;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView recyclerProductos;

  @NonNull
  public final RecyclerView recyclerViewS;

  private ActivityProductosSuzukiBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnAtrasProducSuzuki, @NonNull LinearLayout linearLayout4,
      @NonNull ConstraintLayout main, @NonNull TextView recyclerProductos,
      @NonNull RecyclerView recyclerViewS) {
    this.rootView = rootView;
    this.btnAtrasProducSuzuki = btnAtrasProducSuzuki;
    this.linearLayout4 = linearLayout4;
    this.main = main;
    this.recyclerProductos = recyclerProductos;
    this.recyclerViewS = recyclerViewS;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductosSuzukiBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductosSuzukiBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_productos_suzuki, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductosSuzukiBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAtrasProducSuzuki;
      Button btnAtrasProducSuzuki = ViewBindings.findChildViewById(rootView, id);
      if (btnAtrasProducSuzuki == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.recyclerProductos;
      TextView recyclerProductos = ViewBindings.findChildViewById(rootView, id);
      if (recyclerProductos == null) {
        break missingId;
      }

      id = R.id.recyclerViewS;
      RecyclerView recyclerViewS = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewS == null) {
        break missingId;
      }

      return new ActivityProductosSuzukiBinding((ConstraintLayout) rootView, btnAtrasProducSuzuki,
          linearLayout4, main, recyclerProductos, recyclerViewS);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
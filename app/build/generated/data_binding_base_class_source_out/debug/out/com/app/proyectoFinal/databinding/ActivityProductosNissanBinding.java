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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.proyectoFinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProductosNissanBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAtrasProducNissan;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView recyclerProductos5;

  @NonNull
  public final RecyclerView recyclerViewS;

  private ActivityProductosNissanBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnAtrasProducNissan, @NonNull ImageView imageView9,
      @NonNull LinearLayout linearLayout4, @NonNull ConstraintLayout main,
      @NonNull TextView recyclerProductos5, @NonNull RecyclerView recyclerViewS) {
    this.rootView = rootView;
    this.btnAtrasProducNissan = btnAtrasProducNissan;
    this.imageView9 = imageView9;
    this.linearLayout4 = linearLayout4;
    this.main = main;
    this.recyclerProductos5 = recyclerProductos5;
    this.recyclerViewS = recyclerViewS;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductosNissanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductosNissanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_productos_nissan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductosNissanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAtrasProducNissan;
      Button btnAtrasProducNissan = ViewBindings.findChildViewById(rootView, id);
      if (btnAtrasProducNissan == null) {
        break missingId;
      }

      id = R.id.imageView9;
      ImageView imageView9 = ViewBindings.findChildViewById(rootView, id);
      if (imageView9 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.recyclerProductos5;
      TextView recyclerProductos5 = ViewBindings.findChildViewById(rootView, id);
      if (recyclerProductos5 == null) {
        break missingId;
      }

      id = R.id.recyclerViewS;
      RecyclerView recyclerViewS = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewS == null) {
        break missingId;
      }

      return new ActivityProductosNissanBinding((ConstraintLayout) rootView, btnAtrasProducNissan,
          imageView9, linearLayout4, main, recyclerProductos5, recyclerViewS);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

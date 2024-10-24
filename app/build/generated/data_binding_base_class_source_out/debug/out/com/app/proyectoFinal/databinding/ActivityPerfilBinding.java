// Generated by view binder compiler. Do not edit!
package com.app.proyectoFinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
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

public final class ActivityPerfilBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnRetroceder;

  @NonNull
  public final Button btnregistrarse;

  @NonNull
  public final Button button3;

  @NonNull
  public final TextView editTextText2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imgUser;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final TableLayout tableLayout;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView18;

  @NonNull
  public final TextView txtIdP;

  @NonNull
  public final TextView txtNombres;

  @NonNull
  public final TextView txtdirtext;

  @NonNull
  public final TextView txtgenerotext;

  @NonNull
  public final TextView txtnrodni;

  private ActivityPerfilBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnRetroceder,
      @NonNull Button btnregistrarse, @NonNull Button button3, @NonNull TextView editTextText2,
      @NonNull ImageView imageView3, @NonNull ImageView imgUser, @NonNull ConstraintLayout main,
      @NonNull RelativeLayout relativeLayout, @NonNull TableLayout tableLayout,
      @NonNull TextView textView10, @NonNull TextView textView16, @NonNull TextView textView18,
      @NonNull TextView txtIdP, @NonNull TextView txtNombres, @NonNull TextView txtdirtext,
      @NonNull TextView txtgenerotext, @NonNull TextView txtnrodni) {
    this.rootView = rootView;
    this.btnRetroceder = btnRetroceder;
    this.btnregistrarse = btnregistrarse;
    this.button3 = button3;
    this.editTextText2 = editTextText2;
    this.imageView3 = imageView3;
    this.imgUser = imgUser;
    this.main = main;
    this.relativeLayout = relativeLayout;
    this.tableLayout = tableLayout;
    this.textView10 = textView10;
    this.textView16 = textView16;
    this.textView18 = textView18;
    this.txtIdP = txtIdP;
    this.txtNombres = txtNombres;
    this.txtdirtext = txtdirtext;
    this.txtgenerotext = txtgenerotext;
    this.txtnrodni = txtnrodni;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPerfilBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPerfilBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_perfil, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPerfilBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRetroceder;
      Button btnRetroceder = ViewBindings.findChildViewById(rootView, id);
      if (btnRetroceder == null) {
        break missingId;
      }

      id = R.id.btnregistrarse;
      Button btnregistrarse = ViewBindings.findChildViewById(rootView, id);
      if (btnregistrarse == null) {
        break missingId;
      }

      id = R.id.button3;
      Button button3 = ViewBindings.findChildViewById(rootView, id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.editTextText2;
      TextView editTextText2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextText2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imgUser;
      ImageView imgUser = ViewBindings.findChildViewById(rootView, id);
      if (imgUser == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.tableLayout;
      TableLayout tableLayout = ViewBindings.findChildViewById(rootView, id);
      if (tableLayout == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView18;
      TextView textView18 = ViewBindings.findChildViewById(rootView, id);
      if (textView18 == null) {
        break missingId;
      }

      id = R.id.txtIdP;
      TextView txtIdP = ViewBindings.findChildViewById(rootView, id);
      if (txtIdP == null) {
        break missingId;
      }

      id = R.id.txtNombres;
      TextView txtNombres = ViewBindings.findChildViewById(rootView, id);
      if (txtNombres == null) {
        break missingId;
      }

      id = R.id.txtdirtext;
      TextView txtdirtext = ViewBindings.findChildViewById(rootView, id);
      if (txtdirtext == null) {
        break missingId;
      }

      id = R.id.txtgenerotext;
      TextView txtgenerotext = ViewBindings.findChildViewById(rootView, id);
      if (txtgenerotext == null) {
        break missingId;
      }

      id = R.id.txtnrodni;
      TextView txtnrodni = ViewBindings.findChildViewById(rootView, id);
      if (txtnrodni == null) {
        break missingId;
      }

      return new ActivityPerfilBinding((ConstraintLayout) rootView, btnRetroceder, btnregistrarse,
          button3, editTextText2, imageView3, imgUser, main, relativeLayout, tableLayout,
          textView10, textView16, textView18, txtIdP, txtNombres, txtdirtext, txtgenerotext,
          txtnrodni);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
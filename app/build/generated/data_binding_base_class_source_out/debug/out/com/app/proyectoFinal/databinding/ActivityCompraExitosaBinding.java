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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.proyectoFinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCompraExitosaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonPrint;

  @NonNull
  public final TextView codigoTransaccion;

  @NonNull
  public final TextView fechaPago;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView mainText;

  @NonNull
  public final TextView metodoPago;

  @NonNull
  public final TextView nombreDestinatario;

  @NonNull
  public final TextView pagoTotal;

  @NonNull
  public final TextView pagoTotalAmount;

  @NonNull
  public final TextView subText;

  @NonNull
  public final LinearLayout transactionDetails;

  private ActivityCompraExitosaBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonPrint, @NonNull TextView codigoTransaccion, @NonNull TextView fechaPago,
      @NonNull LinearLayout linearLayout2, @NonNull ConstraintLayout main,
      @NonNull TextView mainText, @NonNull TextView metodoPago,
      @NonNull TextView nombreDestinatario, @NonNull TextView pagoTotal,
      @NonNull TextView pagoTotalAmount, @NonNull TextView subText,
      @NonNull LinearLayout transactionDetails) {
    this.rootView = rootView;
    this.buttonPrint = buttonPrint;
    this.codigoTransaccion = codigoTransaccion;
    this.fechaPago = fechaPago;
    this.linearLayout2 = linearLayout2;
    this.main = main;
    this.mainText = mainText;
    this.metodoPago = metodoPago;
    this.nombreDestinatario = nombreDestinatario;
    this.pagoTotal = pagoTotal;
    this.pagoTotalAmount = pagoTotalAmount;
    this.subText = subText;
    this.transactionDetails = transactionDetails;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCompraExitosaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCompraExitosaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_compra_exitosa, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCompraExitosaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_print;
      Button buttonPrint = ViewBindings.findChildViewById(rootView, id);
      if (buttonPrint == null) {
        break missingId;
      }

      id = R.id.codigo_transaccion;
      TextView codigoTransaccion = ViewBindings.findChildViewById(rootView, id);
      if (codigoTransaccion == null) {
        break missingId;
      }

      id = R.id.fecha_pago;
      TextView fechaPago = ViewBindings.findChildViewById(rootView, id);
      if (fechaPago == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.main_text;
      TextView mainText = ViewBindings.findChildViewById(rootView, id);
      if (mainText == null) {
        break missingId;
      }

      id = R.id.metodo_pago;
      TextView metodoPago = ViewBindings.findChildViewById(rootView, id);
      if (metodoPago == null) {
        break missingId;
      }

      id = R.id.nombre_destinatario;
      TextView nombreDestinatario = ViewBindings.findChildViewById(rootView, id);
      if (nombreDestinatario == null) {
        break missingId;
      }

      id = R.id.pago_total;
      TextView pagoTotal = ViewBindings.findChildViewById(rootView, id);
      if (pagoTotal == null) {
        break missingId;
      }

      id = R.id.pago_total_amount;
      TextView pagoTotalAmount = ViewBindings.findChildViewById(rootView, id);
      if (pagoTotalAmount == null) {
        break missingId;
      }

      id = R.id.sub_text;
      TextView subText = ViewBindings.findChildViewById(rootView, id);
      if (subText == null) {
        break missingId;
      }

      id = R.id.transaction_details;
      LinearLayout transactionDetails = ViewBindings.findChildViewById(rootView, id);
      if (transactionDetails == null) {
        break missingId;
      }

      return new ActivityCompraExitosaBinding((ConstraintLayout) rootView, buttonPrint,
          codigoTransaccion, fechaPago, linearLayout2, main, mainText, metodoPago,
          nombreDestinatario, pagoTotal, pagoTotalAmount, subText, transactionDetails);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

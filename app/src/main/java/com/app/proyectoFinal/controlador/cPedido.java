package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;

public class cPedido extends conexion{

    Context context;
    public cPedido(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Pedido dato){
        conexion con= new cPedido(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("insert into " +tbPedido+" values(' " +
                    dato.getCodigo_p()+ "','" +
                    dato.getCodigo_u()+ "','" +
                    dato.getEstado()+ "','" +
                    dato.getFechaPed()+ "','" +
                    dato.getTotal()+ "')" );
            database.close();
        }
    }

    public void update(Pedido dato){
        conexion con= new cPedido(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("update " +tbPedido+" set(' " +
                    "codigo_ped"+dato.getCodigo_p()+"','" +
                    "codigo_usu"+dato.getCodigo_u()+"','" +
                    "estado"+dato.getEstado()+"','" +
                    "fecha_pedido"+dato.getFechaPed()+"','" +
                    "total"+dato.getTotal()+"','");
            database.close();
        }
    }

    public void delete(Pedido dato){
        conexion con= new cPedido(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("delete from " +tbPedido+" where " +
                    "codigo='"+dato.getCodigo_p()+"'" );
            database.close();
        }
    }

    public ArrayList<Pedido> Select(){
        conexion con= new cPedido(context);
        SQLiteDatabase database=con.getWritableDatabase();
        ArrayList<Pedido> est= new ArrayList<>();
        Cursor dato=null;
        dato=database.rawQuery("select * from "+ tbPedido,null);
        if (dato.moveToFirst()){
            do {
                est.add(new Pedido(
                                Integer.parseInt(dato.getString(0)),
                                Integer.parseInt(dato.getString(1)),
                                dato.getString(2),
                                dato.getString(3),
                                dato.getString(4)
                        )
                );
            }while (dato.moveToNext());
        }
        return est;
    }
}

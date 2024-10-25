package com.app.proyectoFinal.modelo;

public class Pedido {

    public int codigo_p, codigo_u;
    public String fechaPed, estado, total;

    public Pedido(){
        this.codigo_p = 0;
        this.codigo_u = 0;
        this.fechaPed = "";
        this.estado = "";
        this.total = "";
    }

    public Pedido(int codigo_p, int codigo_u, String fechaPed, String estado, String total) {
        this.codigo_p = codigo_p;
        this.codigo_u = codigo_u;
        this.fechaPed = fechaPed;
        this.estado = estado;
        this.total = total;
    }

    public int getCodigo_p() {
        return codigo_p;
    }

    public void setCodigo_p(int codigo_p) {
        this.codigo_p = codigo_p;
    }

    public int getCodigo_u() {
        return codigo_u;
    }

    public void setCodigo_u(int codigo_u) {
        this.codigo_u = codigo_u;
    }

    public String getFechaPed() {
        return fechaPed;
    }

    public void setFechaPed(String fechaPed) {
        this.fechaPed = fechaPed;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo_pedido" + codigo_p +
                ", codigo_usuario" + codigo_u + '\n' +
                ", fecha_pedido" + fechaPed + '\n' +
                ", estado" + estado + '\n' +
                ", total" + total + '\n' +
                "}";
    }
}

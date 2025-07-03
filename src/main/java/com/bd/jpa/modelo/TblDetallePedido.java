package com.bd.jpa.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_detalle_pedido")
public class TblDetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private int iddetalle;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private TblPedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private TblProducto producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private double precio_unitario;

    @Column(name = "subtotal")
    private double subtotal;

    public TblDetallePedido() {
    }

    public TblDetallePedido(int iddetalle, TblPedido pedido, TblProducto producto,
                            int cantidad, double precio_unitario, double subtotal) {
        this.iddetalle = iddetalle;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public TblPedido getPedido() {
        return pedido;
    }

    public void setPedido(TblPedido pedido) {
        this.pedido = pedido;
    }

    public TblProducto getProducto() {
        return producto;
    }

    public void setProducto(TblProducto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}


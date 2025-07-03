package com.bd.jpa.servicio;

import java.util.List;

import com.bd.jpa.modelo.TblPedido;

public interface IPedidoServicio {
    void guardarPedido(TblPedido pedido);
    List<TblPedido> listarPedidos();
}


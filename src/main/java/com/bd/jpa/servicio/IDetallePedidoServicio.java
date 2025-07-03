package com.bd.jpa.servicio;

import java.util.List;

import com.bd.jpa.modelo.TblDetallePedido;

public interface IDetallePedidoServicio {

    void guardarDetallePedido(TblDetallePedido detalle);

    List<TblDetallePedido> listarDetalles();

    TblDetallePedido buscarPorId(int id);

    void eliminarDetalle(int id);
}

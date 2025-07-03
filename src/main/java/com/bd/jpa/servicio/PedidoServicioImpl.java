package com.bd.jpa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.jpa.modelo.TblPedido;
import com.bd.jpa.repositorio.IPedidoRepositorio;

@Service
public class PedidoServicioImpl implements IPedidoServicio {

    @Autowired
    private IPedidoRepositorio pedidoRepositorio;

    @Override
    public void guardarPedido(TblPedido pedido) {
        pedidoRepositorio.save(pedido);
    }

    @Override
    public List<TblPedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }
}



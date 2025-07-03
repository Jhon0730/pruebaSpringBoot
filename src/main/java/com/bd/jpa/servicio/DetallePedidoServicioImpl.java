package com.bd.jpa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.jpa.modelo.TblDetallePedido;
import com.bd.jpa.repositorio.IDetallePedidoRepositorio;

@Service
public class DetallePedidoServicioImpl implements IDetallePedidoServicio {

    @Autowired
    private IDetallePedidoRepositorio detalleRepo;

    @Override
    public void guardarDetallePedido(TblDetallePedido detalle) {
        detalleRepo.save(detalle);
    }

    @Override
    public List<TblDetallePedido> listarDetalles() {
        return detalleRepo.findAll();
    }

    @Override
    public TblDetallePedido buscarPorId(int id) {
        return detalleRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarDetalle(int id) {
        detalleRepo.deleteById(id);
    }
}

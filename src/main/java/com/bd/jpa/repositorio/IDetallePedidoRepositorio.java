package com.bd.jpa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bd.jpa.modelo.TblDetallePedido;

public interface IDetallePedidoRepositorio extends JpaRepository<TblDetallePedido, Integer> {

}
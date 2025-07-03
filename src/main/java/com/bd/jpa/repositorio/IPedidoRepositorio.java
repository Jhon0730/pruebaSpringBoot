package com.bd.jpa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.jpa.modelo.TblPedido;

@Repository
public interface IPedidoRepositorio extends JpaRepository<TblPedido, Integer> {
}

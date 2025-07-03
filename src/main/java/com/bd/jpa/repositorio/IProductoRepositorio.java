package com.bd.jpa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bd.jpa.modelo.TblProducto;

public interface IProductoRepositorio extends JpaRepository<TblProducto, Integer>{

}

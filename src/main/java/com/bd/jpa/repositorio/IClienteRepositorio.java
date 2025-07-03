package com.bd.jpa.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.bd.jpa.modelo.TblCliente;

public interface IClienteRepositorio extends CrudRepository<TblCliente,Integer>{
	TblCliente findByEmail(String email);
}

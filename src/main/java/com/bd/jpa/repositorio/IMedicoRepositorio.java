package com.bd.jpa.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.bd.jpa.modelo.TblMedico;

public interface IMedicoRepositorio extends CrudRepository<TblMedico,Integer> {

}

package com.bd.jpa.servicio;

import java.util.List;

import com.bd.jpa.modelo.TblCliente;

public interface IClienteServicio {

	void RegistrarCliente(TblCliente cliente);
	
	void EliminarCliente(TblCliente cliente);
	List<TblCliente> ListarCliente();
	TblCliente BuscarCliente(Integer id);
	
	TblCliente buscarPorEmail(String email);
}

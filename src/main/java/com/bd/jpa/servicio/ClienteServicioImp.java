package com.bd.jpa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.jpa.modelo.TblCliente;
import com.bd.jpa.repositorio.IClienteRepositorio;

@Service
public class ClienteServicioImp implements IClienteServicio{
	@Autowired
	private IClienteRepositorio iclientereporitorio;
	@Override
	public void RegistrarCliente(TblCliente cliente) {
		// TODO Auto-generated method stub
		iclientereporitorio.save(cliente);
	}

	@Override
	public void EliminarCliente(TblCliente cliente) {
		// TODO Auto-generated method stub
		iclientereporitorio.deleteById(cliente.getIdcliente());
		
	}

	@Override
	public List<TblCliente> ListarCliente() {
		// TODO Auto-generated method stub
		return (List<TblCliente>)iclientereporitorio.findAll();
	}

	@Override
	public TblCliente BuscarCliente(Integer id) {
		// TODO Auto-generated method stub
		return iclientereporitorio.findById(id).orElse(null);
	}
	@Override
	public TblCliente buscarPorEmail(String email) {
		return iclientereporitorio.findByEmail(email);
	}

}

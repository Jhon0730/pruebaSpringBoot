package com.bd.jpa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.jpa.modelo.TblMedico;
import com.bd.jpa.repositorio.IMedicoRepositorio;
@Service
public class MedicoServicioImp implements IMedicoServicio{
	@Autowired
	private IMedicoRepositorio idmedicorepositorio;
	
	@Override
	public void RegistrarMedico(TblMedico tblmed) {
		idmedicorepositorio.save(tblmed);
		
	}

	@Override
	public void ActualizarMedico(TblMedico tblmed) {
		idmedicorepositorio.deleteById(tblmed.getIdmedico());
		
	}

	@Override
	public List<TblMedico> ListadoMedico() {
		
		return (List<TblMedico>)idmedicorepositorio.findAll();
	}

	@Override
	public TblMedico BuscarporId(Integer idmedico) {
		return idmedicorepositorio.findById(idmedico).orElse(null);
	}

}

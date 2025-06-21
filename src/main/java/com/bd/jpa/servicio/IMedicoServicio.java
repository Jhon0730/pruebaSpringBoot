package com.bd.jpa.servicio;

import java.util.List;

import com.bd.jpa.modelo.TblMedico;

public interface IMedicoServicio {
	void RegistrarMedico(TblMedico tblmed);
	void ActualizarMedico(TblMedico tblmed);
	List<TblMedico>ListadoMedico();
	TblMedico BuscarporId (Integer idmedico);
}

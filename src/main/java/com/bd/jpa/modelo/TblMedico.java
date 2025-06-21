package com.bd.jpa.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="tbl_medico")
public class TblMedico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmedico;
	private String nomt2 ;
	private String apellidot2;
	private String emailt2;
	private String dnit2;
	private String estadodoc;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date fechanac;
	
	public int getIdmedico() {
		return idmedico;
	}
	public void setIdmedico(int idmedico) {
		this.idmedico = idmedico;
	}
	public String getNomt2() {
		return nomt2;
	}
	public void setNomt2(String nomt2) {
		this.nomt2 = nomt2;
	}
	public String getApellidot2() {
		return apellidot2;
	}
	public void setApellidot2(String apellidot2) {
		this.apellidot2 = apellidot2;
	}
	public String getEmailt2() {
		return emailt2;
	}
	public void setEmailt2(String emailt2) {
		this.emailt2 = emailt2;
	}
	public String getDnit2() {
		return dnit2;
	}
	public void setDnit2(String dnit2) {
		this.dnit2 = dnit2;
	}
	public String getEstadodoc() {
		return estadodoc;
	}
	public void setEstadodoc(String estadodoc) {
		this.estadodoc = estadodoc;
	}
	public Date getFechanac() {
		return fechanac;
	}
	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}
	public TblMedico() {
		//super();
	}
	public TblMedico(String nomt2, String apellidot2, String emailt2, String dnit2, String estadodoc, Date fechanac) {
		//super();
		
		this.nomt2 = nomt2;
		this.apellidot2 = apellidot2;
		this.emailt2 = emailt2;
		this.dnit2 = dnit2;
		this.estadodoc = estadodoc;
		this.fechanac = fechanac;
	}
	public TblMedico(int idmedico) {
		super();
		this.idmedico = idmedico;
	}
	
	
}

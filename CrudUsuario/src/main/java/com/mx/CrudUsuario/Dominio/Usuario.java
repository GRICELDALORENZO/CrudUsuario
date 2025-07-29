package com.mx.CrudUsuario.Dominio;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario {
	/*
	 * ID_USUARIO NUMBER, 
    NOMBRE NVARCHAR2(100),
    APP NVARCHAR2(50),
    APM NVARCHAR2(50),
    SEXO NVARCHAR2(25),
    CORREO NVARCHAR2(100),
    FECHA_NACIMIENTO DATE,
    FECHA_CREACION DATE,
    ROL_ID NUMBER,
	 */
	@Id
	@Column
	private int idUsuario;
	
	@Column
	private String nombre;
	
	@Column
	private String app;
	
	@Column
	private String apm;
	
	@Column
	private String sexo;
	
	@Column
	private String correo;
	
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;

	 @Column(name = "FECHA_CREACION")
	 private LocalDate fechaCreacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ROL_ID")
	private Roles rolId;

	public Usuario() {
	
	}
	
	
	
	
	public Usuario(int idUsuario, String nombre, String app, String apm, String sexo, String correo,
			LocalDate fechaNacimiento, LocalDate fechaCreacion, Roles rolId) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.app = app;
		this.apm = apm;
		this.sexo = sexo;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaCreacion = fechaCreacion;
		this.rolId = rolId;
	}




	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", app=" + app + ", apm=" + apm + ", sexo="
				+ sexo + ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento + ", fechaCreacion="
				+ fechaCreacion + ", rolId=" + rolId + "]\n";
	}




	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getApm() {
		return apm;
	}

	public void setApm(String apm) {
		this.apm = apm;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
	public Roles getRolId() {
		return rolId;
	}

	public void setRolId(Roles rolId) {
		this.rolId = rolId;
	}




	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}




	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}




	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}




	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
	
}

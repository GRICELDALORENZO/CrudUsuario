package com.mx.RolMS.Entidad;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	private String nombre;
	private String app;
	private String apm;
	private String sexo;
	private String correo;
	private LocalDate fechaNacimiento;
	private LocalDate fechaCreacion;
	private int rolId;
}

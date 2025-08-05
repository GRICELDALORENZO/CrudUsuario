package com.mx.UsuarioMS.Dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Usuario {
	 /* ID_USUARIO NUMBER, 
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

package com.mx.CrudUsuario.Dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ROLES")
public class Roles {
	/*
	 * ID_ROL NUMBER,
    PRIVILEGIO NVARCHAR2(50),
	 */
	@Id
	@Column
	private int idRol;
	
	@Column
	private String privilegio;
	
	@OneToMany(mappedBy = "rolId", cascade = CascadeType.ALL)
	List<Usuario> lista = new ArrayList<>();

	public Roles() {
		
	}

	public Roles(int idRol, String privilegio) {
		super();
		this.idRol = idRol;
		this.privilegio = privilegio;
	}
	
	

	@Override
	public String toString() {
		return "Roles [idRol=" + idRol + ", privilegio=" + privilegio + "]\n";
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}
	
	
}

package com.mx.RolMS.Service;

import java.util.List;

import com.mx.RolMS.Dominio.Rol;

public interface IRolService {

	Rol guardar(Rol r);
	
	List<Rol> listar();
	
	Rol buscar(int idRol);
	
	void eliminar(int idRol);
}

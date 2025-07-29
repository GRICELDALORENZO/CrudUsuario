package com.mx.CrudUsuario.Service;

import java.util.List;

public interface IMetodos<T> {
	
	void guardar(T entidad);
	
	void editar(T entidad);
	
	void eliminar(T entidad);
	
	List<T> listar();
	
	T buscar(T entidad);
}

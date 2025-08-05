package com.mx.UsuarioMS.Service;

import java.util.List;

import com.mx.UsuarioMS.Dominio.Usuario;

public interface IUsuarioService {
	
	Usuario guardar(Usuario u);
	
	List<Usuario> listar();
	
	Usuario buscar(int idUsuario);
	
	void eliminar(int idUsuario);

}

package com.mx.UsuarioMS.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.UsuarioMS.Dominio.Usuario;
import com.mx.UsuarioMS.Repositoryç.IUsuarioRepository;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

   
	@Autowired
	private IUsuarioRepository repository;

   

	@Override
	public Usuario guardar(Usuario u) {
		// TODO Auto-generated method stub
		return repository.save(u);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.ASC, "idUsuario"));
	}

	@Override
	public Usuario buscar(int idUsuario) {
		// TODO Auto-generated method stub
		return repository.findById(idUsuario).orElse(null);
	}

	@Override
	public void eliminar(int idUsuario) {
		// TODO Auto-generated method stub
		repository.deleteById(idUsuario);
	}
	
	
	public Usuario validacion(String nombre, String app, String apm) {
		return repository.findByNombreIgnoringCaseContainingAndAppIgnoringCaseContainingAndApmIgnoringCaseContaining(nombre, app, apm);
	}
	
	public List<Usuario> porRol(int rolId){
		return repository.findByRolId(rolId);
	}

}

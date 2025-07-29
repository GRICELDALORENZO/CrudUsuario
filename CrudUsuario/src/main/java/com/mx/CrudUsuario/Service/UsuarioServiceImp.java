package com.mx.CrudUsuario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.mx.CrudUsuario.DAO.IUsuarioDao;
import com.mx.CrudUsuario.Dominio.Roles;
import com.mx.CrudUsuario.Dominio.Usuario;

@Service
public class UsuarioServiceImp implements IMetodos<Usuario>{

    
	@Autowired
	private IUsuarioDao dao;

   
	
	@Override
	public void guardar(Usuario entidad) {
		// TODO Auto-generated method stub
		dao.save(entidad);
	}

	@Override
	public void editar(Usuario entidad) {
		// TODO Auto-generated method stub
		dao.save(entidad);
	}

	@Override
	public void eliminar(Usuario entidad) {
		// TODO Auto-generated method stub
		dao.delete(entidad);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return dao.findAll(Sort.by(Sort.Direction.ASC,"idUsuario"));
	}

	@Override
	public Usuario buscar(Usuario entidad) {
		// TODO Auto-generated method stub
		return dao.findById(entidad.getIdUsuario()).orElse(null);
	}
	public List<Usuario> porRolId(Roles rolId){
		return dao.findByRolId(rolId);
	}
	
	public Usuario poNombre(String nombre, String app, String apm) {
		return dao.findByNombreIgnoringCaseContainingAndAppIgnoringCaseContainingAndApmIgnoringCaseContaining(nombre, app, apm);
	}

}

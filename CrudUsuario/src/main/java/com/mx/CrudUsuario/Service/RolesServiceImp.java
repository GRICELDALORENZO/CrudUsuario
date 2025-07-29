package com.mx.CrudUsuario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.CrudUsuario.DAO.IRolesDao;
import com.mx.CrudUsuario.Dominio.Roles;

@Service
public class RolesServiceImp implements IMetodos<Roles> {

	@Autowired
	private IRolesDao dao;
	
	@Override
	public void guardar(Roles entidad) {
		// TODO Auto-generated method stub
		dao.save(entidad);
	}

	@Override
	public void editar(Roles entidad) {
		// TODO Auto-generated method stub
		dao.save(entidad);
	}

	@Override
	public void eliminar(Roles entidad) {
		// TODO Auto-generated method stub
		dao.delete(entidad);
	}

	@Override
	public List<Roles> listar() {
		// TODO Auto-generated method stub
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idRol"));
	}

	@Override
	public Roles buscar(Roles entidad) {
		// TODO Auto-generated method stub
		return dao.findById(entidad.getIdRol()).orElse(null);
	}
	
	public Roles porPrivilegio(String privilegio) {
		return dao.findByPrivilegioIgnoringCaseContaining(privilegio);
	}
	
	public Roles porIdRol(int idRol) {
		return dao.findByIdRol(idRol);
	}

}

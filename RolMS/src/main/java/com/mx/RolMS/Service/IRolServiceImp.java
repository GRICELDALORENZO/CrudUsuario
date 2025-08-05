package com.mx.RolMS.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.RolMS.ClientFeign.IUsuarioClientFeign;
import com.mx.RolMS.DAO.IRolDao;
import com.mx.RolMS.Dominio.Rol;
import com.mx.RolMS.Entidad.Usuario;

@Service
public class IRolServiceImp implements IRolService{

	@Autowired
	IRolDao dao;

	@Override
	public Rol guardar(Rol r) {
		// TODO Auto-generated method stub
		return dao.save(r);
	}

	@Override
	public List<Rol> listar() {
		// TODO Auto-generated method stub
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idRol"));
	}

	@Override
	public Rol buscar(int idRol) {
		// TODO Auto-generated method stub
		return dao.findById(idRol).orElse(null);
	}

	@Override
	public void eliminar(int idRol) {
		// TODO Auto-generated method stub
		dao.deleteById(idRol);
		
	}
	
	public List<Rol> porPrivilegio(String privilegio){
		return dao.findByPrivilegio(privilegio);
	}
	
	@Autowired
	private IUsuarioClientFeign uFeignCliet;
	
	public List<Usuario> traerUsuarios(int rolId){
		return uFeignCliet.porRolId(rolId); 
	}
	public List<Object> rolUsuarios(int idRol){
		List<Object> respuesta = new ArrayList<>();
		Rol encontrado = dao.findById(idRol).orElse(null);
		if(encontrado == null) {
			respuesta.add("NO EXISTE EL ROL "+idRol);
			
		}else {
			respuesta.add(encontrado);
			List<Usuario> usuarios = uFeignCliet.porRolId(idRol);
			if(usuarios == null || usuarios.isEmpty()) {
				respuesta.add("NO EXISTE EL USUARIO");
			}else {
				respuesta.add(usuarios);
			}
		}
		return respuesta;
	}
	
	
	
}

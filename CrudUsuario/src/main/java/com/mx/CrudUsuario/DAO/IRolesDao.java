package com.mx.CrudUsuario.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.CrudUsuario.Dominio.Roles;

@Repository
public interface IRolesDao extends JpaRepository<Roles, Integer> {
	
	
	Roles findByPrivilegioIgnoringCaseContaining(String privilegio);
	Roles findByIdRol(int idRol);

}

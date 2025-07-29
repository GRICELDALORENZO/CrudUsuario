package com.mx.CrudUsuario.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.CrudUsuario.Dominio.Roles;
import com.mx.CrudUsuario.Dominio.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{
	List<Usuario> findByRolId(Roles idRol);
	Usuario findByNombreIgnoringCaseContainingAndAppIgnoringCaseContainingAndApmIgnoringCaseContaining(String nombre, String app, String apm);


}

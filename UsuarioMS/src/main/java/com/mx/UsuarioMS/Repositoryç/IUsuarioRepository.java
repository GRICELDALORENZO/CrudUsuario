package com.mx.UsuarioMS.Repositoryç;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.UsuarioMS.Dominio.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByNombreIgnoringCaseContainingAndAppIgnoringCaseContainingAndApmIgnoringCaseContaining(String nombre, String app, String apm);
	
	List<Usuario> findByRolId(int RolId);
}

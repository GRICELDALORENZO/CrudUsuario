package com.mx.RolMS.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.RolMS.Dominio.Rol;

public interface IRolDao extends JpaRepository<Rol, Integer> {
	
	List<Rol> findByPrivilegio(String privilegio);

}

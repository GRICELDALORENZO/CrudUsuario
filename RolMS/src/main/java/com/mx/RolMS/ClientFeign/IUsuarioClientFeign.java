package com.mx.RolMS.ClientFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.RolMS.Entidad.Usuario;

@FeignClient(name="UsuarioMS", url="http://localhost:8011", path="/usuario")
public interface IUsuarioClientFeign {
	
	@GetMapping(value="/rol/{RolId}")
	public List<Usuario> porRolId(@PathVariable int RolId);
	

	}

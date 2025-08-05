package com.mx.UsuarioMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.UsuarioMS.Dominio.Usuario;
import com.mx.UsuarioMS.Service.IUsuarioServiceImp;

@RestController
@RequestMapping(path="/usuario")
//@CrossOrigin
public class UsuarioMS {

	@Autowired
	private IUsuarioServiceImp service;
	
	//http://localhost:8011/usuario/listar
	//LISTAR
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(){
		List<Usuario> usuario = service.listar();
		return(usuario.isEmpty())? ResponseEntity.noContent().build() : ResponseEntity.ok(usuario);
	}
		//guardar  http://localhost:8011/usuario/guardar
	@PostMapping(value="/guardar")
	public ResponseEntity<?> guardar(@RequestBody Usuario u){
		Usuario existe = service.validacion(u.getNombre(), u.getApp(), u.getApm());
		if(existe != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}else {
			existe = service.guardar(u);
			return ResponseEntity.status(HttpStatus.CREATED).body(existe);	}
	}
	//editar http://localhost:8011/usuario/editar
	@PutMapping(value="/editar")
	public ResponseEntity<?> editar(@RequestBody Usuario u){
		Usuario actual = service.guardar(u);
		return ResponseEntity.ok(actual);
	}
	// eliminar   http://localhost:8011/usuario/eliminar
	@DeleteMapping(value="eliminar/{idUsuario}")
	public ResponseEntity<?> eliminar(@PathVariable int idUsuario){
		service.eliminar(idUsuario);
		return ResponseEntity.noContent().build();
	}
	//http://localhost:8011/usuario/buscar
	@PostMapping("buscar")
	public ResponseEntity<?> buscar(@RequestParam int idUsuario){
		Usuario encontrado = service.buscar(idUsuario);
		return(encontrado != null)? ResponseEntity.ok(encontrado) : ResponseEntity.notFound().build();
	}
	//porRolId http://localhost:8011/usuario/rol
	@GetMapping(value="/rol/{RolId}")
	public ResponseEntity<?> porRolId(@PathVariable int RolId){
		List<Usuario> u=service.porRol(RolId);
		return(u.isEmpty())? ResponseEntity.noContent().build() : ResponseEntity.ok(u);
	}
	
	
}

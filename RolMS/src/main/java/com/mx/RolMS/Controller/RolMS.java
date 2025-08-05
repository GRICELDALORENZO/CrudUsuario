package com.mx.RolMS.Controller;

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

import com.mx.RolMS.Dominio.Rol;
import com.mx.RolMS.Service.IRolServiceImp;

@RestController
@RequestMapping(path="rol")
//@CrossOrigin
public class RolMS {

	// http://localhost:8010/rol/
	
	@Autowired
	private IRolServiceImp service;
	
	//listar http://localhost:8010/rol/listar
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(){
		List<Rol> r = service.listar();
		return(r.isEmpty())? ResponseEntity.noContent().build() : ResponseEntity.ok(r);
	}
	
	//guardar http://localhost:8010/rol/guardar
	@PostMapping(value="/guardar")
	public ResponseEntity<?> guardar(@RequestBody Rol r){
		Rol nurvo = service.guardar(r);
		return ResponseEntity.status(HttpStatus.CREATED).body(nurvo);
	}
	
	//EDITAR http://localhost:8010/rol/editar
	@PutMapping(value="/editar")
	public ResponseEntity<?> editar(@RequestBody Rol r){
		Rol actual = service.guardar(r);
		return ResponseEntity.ok(actual);
	}
	
	//elimnar http://localhost:8010/rol/eliminar
	@DeleteMapping(value="eliminar/{idRol}")
	public ResponseEntity<?> eliminar(@PathVariable int idRol){
		service.eliminar(idRol);
		return ResponseEntity.noContent().build();
	}
	
	//buscar http://localhost:8010/rol/buscar
	@PostMapping(value="/buscar")
	public ResponseEntity<?> buscar(@RequestParam int idRol){
		Rol encontrado = service.buscar(idRol);
		return(encontrado == null)? ResponseEntity.noContent().build() : ResponseEntity.ok(encontrado);
	}
	
	//poPrivilegio http://localhost:8010/rol/
	@GetMapping(value="privilegio/{privilegio}")
	public ResponseEntity<?> porPrivilegio(@PathVariable String privilegio){
		List<Rol> r = service.porPrivilegio(privilegio);
		return(r.isEmpty())? ResponseEntity.noContent().build() : ResponseEntity.ok(r);
	}
	
	//http://localhost:8010/rol/usuario
	@GetMapping("usuario/{rolId}")
	public ResponseEntity<?> usuarios(@PathVariable int rolId){
		List<Object> lista=service.rolUsuarios(rolId);
		return(lista.isEmpty())? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
	}
	
	
}

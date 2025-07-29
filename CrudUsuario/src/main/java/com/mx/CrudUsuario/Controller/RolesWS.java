package com.mx.CrudUsuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.CrudUsuario.Dominio.Roles;
import com.mx.CrudUsuario.Dominio.Usuario;
import com.mx.CrudUsuario.Service.RolesServiceImp;
import com.mx.CrudUsuario.Service.UsuarioServiceImp;

@RestController
@RequestMapping(path="api/rol")
@CrossOrigin
public class RolesWS {
	
	@Autowired
	private RolesServiceImp service;
	
	//listar -->  http://localhost:8003/api/rol/listar
	@GetMapping(path="/listar")
	public ResponseEntity<?> listar(){
		List<Roles> roles=service.listar();
		if(roles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(roles);
		}
	}
	
	//GUARDAR http://localhost:8003/api/rol/guardar
	@PostMapping(path="/guardar")
	public ResponseEntity<?> guardar(@RequestBody Roles r){
		
		Roles nuevo= service.buscar(r);
	    Roles existe = service.porPrivilegio(r.getPrivilegio());
		
	   if(nuevo != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL ROL CON ID " +r.getIdRol()+" YA EXISTE.\"}" );
			
			} if(existe != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL ROL CON EL PRIVILEGIO "+r.getPrivilegio()+" YA EXISTE.\"}");
			}else
			{
			service.guardar(r);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\":\"SE HA ALMACENADO EL ROL "+r.getIdRol()+".\"}");
		
			}
	}
	
	//EDITAR http://localhost:8003/api/rol/editar
	@PutMapping(path="/editar")
	public ResponseEntity<?> editar(@RequestBody Roles r){
		Roles nuevo= service.buscar(r);
	    Roles existe = service.porPrivilegio(r.getPrivilegio());
		
	    if(nuevo == null) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL ROL NO EXISTE "+r.getIdRol()+ " NO HA SIDO ACTUALIZADO.\"}");
	    }else if(existe != null) {
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL ROL CON PRIVILEGIO "+r.getPrivilegio()+ " YA EXISTE.\"}");
	    }
	    	service.editar(r);
	    	return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"SE ACTUALIZO EL ROL "+r.getIdRol()+".\"}");
	}
	
	@Autowired
	private UsuarioServiceImp serviceUsuario;
	//ELIMINAR http://localhost:8003/api/rol/eliminar
	@DeleteMapping(path="/eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Roles r){
		Roles roles = service.porIdRol(r.getIdRol());
		List<Usuario> encontrado = serviceUsuario.porRolId(r);
		
		if(roles == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL ROL "+ r.getIdRol()+ " NO EXISTE.\"}");
		}
		if(encontrado.isEmpty()) {
			service.eliminar(r);
			return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"SE ELIMINO EL ROL " +r.getIdRol()+".\"}");
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"NO SE PUEDE ELIMINAR EL ROL CON ID "+r.getIdRol()+" POR QUE TIENE USUARIOS ASOCIADOS.\"}");
		}
	}
	
	// http://localhost:8003/api/rol/buscar
	@PostMapping(path="/buscar")
	public ResponseEntity<?> buscar(@RequestBody Roles r){
		Roles roles = service.buscar(r);
		if(roles == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL ROL CON ID "+r.getIdRol()+" NO HA SIDO LOCALIZADO.\"}");
		}else {
			return ResponseEntity.ok(roles);
		}
	}

}

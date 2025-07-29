package com.mx.CrudUsuario.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.CrudUsuario.Dominio.Roles;
import com.mx.CrudUsuario.Dominio.Usuario;
import com.mx.CrudUsuario.Service.RolesServiceImp;
import com.mx.CrudUsuario.Service.UsuarioServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="api/usuario")
@CrossOrigin
public class UsuarioWS {
	
	@Autowired
	private UsuarioServiceImp service;
	@Autowired
	private RolesServiceImp serviceRol;
	
	//listar http://localhost:8003/api/usuario/listar
	@GetMapping(path="/listar")
	public ResponseEntity<?> listar(){
		List<Usuario> usuarios=service.listar();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(usuarios);
		}
	}
	
	//GUARDAR http://localhost:8003/api/usuario/guardar
	@PostMapping(path = "/guardar")
	public ResponseEntity<?> guardar(@RequestBody Usuario u){
		
		Usuario usuario = service.buscar(u);
		Usuario encontrado  =service.poNombre(u.getNombre(), u.getApp(), u.getApm());
		Roles rol = serviceRol.buscar(u.getRolId());
		String letras = "^[A-Za-z]+$";
		
		if(encontrado != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL USUARIO CON NOMBRE " +u.getNombre()+" "+u.getApp()+ " " + u.getApm()+ " YA EXISTE.\"}");
		}if(rol == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL ROL NO EXISTE.\"}");
		} 
		
	    if (!u.getNombre().matches(letras) || !u.getApp().matches(letras) || !u.getApm().matches(letras)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body("{\"mensaje\":\"NOMBRE Y APELLIDOS SOLO PUEDEN CONTENER LETRAS.\"}");
	    }
		
		if (Period.between(u.getFechaNacimiento(), LocalDate.now()).getYears() < 18) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"DEBE SER MAYOR DE 18 AÑOS.\"}");
	    }
		
		if (usuario == null) {
			service.guardar(u);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\":\"SE GUARDO EL USUARIO "+u.getNombre()+".\"}");
		}
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL USUARIO CON ID "+ u.getIdUsuario()+" YA EXISTE NO SE ALMACENO.\"}");
		
	}
	
	//EDITAR http://localhost:8003/api/usuario/editar
	@PutMapping(path="/editar")
	public ResponseEntity<?> editar(@RequestBody Usuario u){
		Usuario usuario = service.buscar(u);
		Roles rol = serviceRol.buscar(u.getRolId());
		
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL USUARIO "+u.getIdUsuario()+" NO EXISTE. NO SE EDITO.\"}");
		}else if(rol== null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"EL ROL NO EXISTE, NO SE ACTUALIZO USUARIO.\"}");
		}else if (Period.between(u.getFechaNacimiento(), LocalDate.now()).getYears() < 18) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\":\"DEBE SER MAYOR DE 18 AÑOS.\"}");
	    }
			service.editar(u);
			return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"SE ACTUALIZO EL USUARIO "+u.getIdUsuario()+".\"}");
	}
	
	//ELIMINAR http://localhost:8003/api/usuario/eliminar
	@DeleteMapping(path="/eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Usuario u ){
		Usuario usuarios = service.buscar(u);
		if(usuarios == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL USUARIO CON ID "+u.getIdUsuario()+ " NO EXISTE.\"}");
		}
		service.eliminar(u);
		return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"SE ELIMINO EL ALUMNO CON ID "+u.getIdUsuario()+".\"}");
	}
	
	//BUSCAR http://localhost:8003/api/usuario/buscar
	@PostMapping(path="/buscar")
	public ResponseEntity<?> busacr(@RequestBody Usuario u){
		Usuario usuario = service.buscar(u);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"EL ALUMNO "+u.getIdUsuario()+ " NO SE HA LOCALIZADO .\"}");
		}
		return ResponseEntity.ok(usuario);
	}
	

}

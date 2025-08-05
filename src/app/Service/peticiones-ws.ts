import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Roles } from '../Entidad/Roles';
import { Usuario } from '../Entidad/Usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PeticionesWS {
  
  //inicializar una variable como cliente http
  constructor(private http: HttpClient){}

  //crear una variable para url de backend
  url = "http://localhost:9001/rol"
  url2= "http://localhost:9001/usuario"

  //crear una peticion 
  listar(){
    return this.http.get<Roles[]>(this.url+"/listar");
  }
  guardar(roles: Roles){
    return this.http.post<String>(this.url+"/guardar", roles);
  }
  buscar(idRol: number){
    const params = new HttpParams().set("idRol", idRol.toString());
    return this.http.post<Roles>(this.url+"/buscar", null, {params});
  }
  editar(roles: Roles){
    return this.http.put<String>(this.url+"/editar", roles);
  }
  eliminar(idRol: Number){
    return this.http.delete<void>(`${this.url}/eliminar/${idRol}`);
  }

  //usuario
  listarU(){
    return this.http.get<Usuario[]>(this.url2+"/listar");
  }
  guardarU(usuario: Usuario){
    return this.http.post<String>(this.url2+"/guardar", usuario);
  }
  buscarU(idUsuario: Number){
    const params = new HttpParams().set("idUsuario", idUsuario.toString());
    return this.http.post<Usuario>(this.url2+"/buscar", null, {params});
  }
  editarU(usuario: Usuario){
    return this.http.put<String>(this.url2+"/editar", usuario);
  }
  eliminarU(idUsuario: Number){
    return this.http.delete<void>(`${this.url2}/eliminar/${idUsuario}`);    
  }

  usuariosPorRol(idRol: Number){
  return this.http.get<{ rol: Roles, usuarios: Usuario[] }>(`${this.url}/usuario/${idRol}`);

}

}

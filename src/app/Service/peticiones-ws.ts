import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Roles } from '../Entidad/Roles';
import { Usuario } from '../Entidad/Usuario';

@Injectable({
  providedIn: 'root'
})
export class PeticionesWS {
  
  //inicializar una variable como cliente http
  constructor(private http: HttpClient){}

  //crear una variable para url de backend
  url = "http://localhost:8003/api/rol"
  url2 = "http://localhost:8003/api/usuario"

  //crear una peticion 
  listar(){
    return this.http.get<Roles[]>(this.url+"/listar");
  }
  guardar(roles: Roles){
    return this.http.post<String>(this.url+"/guardar", roles);
  }
  buscar(roles: Roles){
    return this.http.post<Roles>(this.url+"/buscar", roles);
  }
  editar(roles: Roles){
    return this.http.put<String>(this.url+"/editar", roles);
  }
  eliminar(roles: Roles){
    return this.http.delete<void>(this.url+"/eliminar", {body:roles});
  }

  //usuario
  listarU(){
    return this.http.get<Usuario[]>(this.url2+"/listar");
  }
  guardarU(usuario: Usuario){
    return this.http.post<String>(this.url2+"/guardar", usuario);
  }
  buscarU(usuario: Usuario){
    return this.http.post<Usuario>(this.url2+"/buscar", usuario);
  }
  editarU(usuario: Usuario){
    return this.http.put<String>(this.url2+"/editar", usuario);
  }
  eliminarU(usuario: Usuario){
    return this.http.delete<void>(this.url2+"/eliminar", {body:usuario});
  }

}

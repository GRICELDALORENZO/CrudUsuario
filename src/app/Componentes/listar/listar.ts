import { Component } from '@angular/core';
import { PeticionesWS } from '../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { Roles } from '../../Entidad/Roles';
import Swal from 'sweetalert2';
import { Usuario } from '../../Entidad/Usuario';

@Component({
  selector: 'app-listar',
  imports: [],
  templateUrl: './listar.html',
  styleUrl: './listar.css'
})
export class Listar {
 constructor(private peticion:PeticionesWS, private router:Router){}
  roles !: Roles[];
  rol = new Roles();
  usuariosRol: Usuario[] = [];
  rolSeleccionado: Number | null = null;


 //metodo
 ngOnInit():void{
  this.listar();

 }

 listar(){
  this.peticion.listar().subscribe(data =>{
    console.log(JSON.stringify(data));
    this.roles=data;
  })
 }

 guardar(){
  this.router.navigate(['guardar']);
 }

 editar(idRol:Number){
  localStorage.setItem("idRol",idRol.toString());
  this.router.navigate(['editar']);
 }

 eliminar(rol: Roles){
  Swal.fire({
    title: '¿Estás seguro?',
    text: `Eliminar el Rol "${rol.idRol}"`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: 'Sí, eliminar'
  }).then(data => {
    if (data.isConfirmed) {
      this.peticion.eliminar(rol.idRol).subscribe(() => {
       Swal.fire({
      title: 'Eliminado!',
      text: `El Rol " ${rol.idRol}" ha sido eliminado."`,
      icon: 'success'
    });
        this.listar(); // Vuelve a cargar la lista
      });
    }
  });
 }

 verUsuarios(idRol: Number) {
  this.peticion.usuariosPorRol(idRol).subscribe({ // Se hace una petición al backend para obtener usuarios por rol
    next: (data: any) => { // Se espera que el backend regrese un arreglo con dos elementos:
      // next: (data: { rol: Roles, usuarios: Usuario[] })
                                         // [0] = objeto Rol
                                         // [1] = arreglo de objetos Usuario


      const rol = data[0];            // Se guarda el primer elemento como 'rol'
      const usuarios = data[1]; // Se guarda el elemento como usuarios

      if (!Array.isArray(usuarios) || usuarios.length === 0) {
        Swal.fire('Sin usuarios asignados a este rol');
        
        return;
      }

      // Tabla usando clases de Bootstrap
      let tablaUsuarios = `
        <table class="table table-hover">
          <thead >
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido Paterno</th>
              <th>Apellido Materno</th>
              <th>Correo</th>
              <th>ROL ID</th>
            </tr>
          </thead>
          <tbody>
      `;
        // Recorremos cada usuario y creamos una fila de tabla para cada uno
      tablaUsuarios += usuarios.map((u: Usuario) => `
        <tr>
          <td>${u.idUsuario}</td>
          <td>${u.nombre}</td>
          <td>${u.app}</td>
          <td>${u.apm}</td>
          <td>${u.correo}</td>
          <td>${u.rolId}</td>
        </tr>
      `).join('');// .join('') para unir el array en una cadena

      tablaUsuarios += `</tbody></table>`;

      Swal.fire({
        title: `Usuarios del Rol ${rol.privilegio} ID ${rol.idRol}`,
        html: tablaUsuarios,
        width: '90%',
        confirmButtonText: 'OK'
      });
    }
  });
}

}

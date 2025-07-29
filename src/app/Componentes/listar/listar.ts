import { Component } from '@angular/core';
import { PeticionesWS } from '../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { Roles } from '../../Entidad/Roles';
import Swal from 'sweetalert2';

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
      this.peticion.eliminar(rol).subscribe(() => {
       Swal.fire({
      title: 'Eliminado!',
      text: `El Rol " ${rol.idRol}" ha sido eliminado."`,
      icon: 'success'
    });
        this.listar(); // Vuelve a cargar la lista
      }, error => {
        Swal.fire('Error', 'No se pudo eliminar el Rol. Esta siendo Utilizado por un Usuario', 'error');
      });
    }
  });
 }
}

import { Component } from '@angular/core';
import { PeticionesWS } from '../../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { Usuario } from '../../../Entidad/Usuario';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-usuario',
  imports: [],
  templateUrl: './listar-usuario.html',
  styleUrl: './listar-usuario.css'
})
export class ListarUsuario {
   //Inicializar la clase peticion
  constructor(private peticion:PeticionesWS, private router:Router){}

  

  //metodo
  ngOnInit():void{
    this.listarUsuario();
  }
  
    usuarios !: Usuario[];

  listarUsuario(){
    this.peticion.listarU().subscribe(data =>{
      console.log(JSON.stringify(data));
      this.usuarios=data;
    })
  }
  guardarUsuario(){
    this.router.navigate(['guardarUsuario'])
  }
  
  editar(idUsuario:Number){
    localStorage.setItem("idUsuario",idUsuario.toString());
    this.router.navigate(['editarUsuario']);
  }

  eliminar(usuario:Usuario){
     Swal.fire({
        title: '¿Estás seguro?',
        text: `Eliminar al Usuario "${usuario.idUsuario}"`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: 'Sí, eliminar'
      }).then(data => {
        if (data.isConfirmed) {
          this.peticion.eliminarU(usuario.idUsuario).subscribe(() => {
           Swal.fire({
          title: 'Eliminado!',
          text: `El Usuario " ${usuario.idUsuario}" ha sido eliminado."`,
          icon: 'success'
        });
            this.listarUsuario(); // Vuelve a cargar la lista
          }, error => {
            Swal.fire({
              text: JSON.stringify(error.error.mensaje)
            }
              
            );
          });
        }
      });
  }

}

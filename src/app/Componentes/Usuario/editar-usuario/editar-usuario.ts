import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PeticionesWS } from '../../../Service/peticiones-ws';
import { Roles } from '../../../Entidad/Roles';
import { Usuario } from '../../../Entidad/Usuario';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar-usuario',
  imports: [FormsModule],
  templateUrl: './editar-usuario.html',
  styleUrl: './editar-usuario.css'
})
export class EditarUsuario implements OnInit{
  constructor(private peticion:PeticionesWS, private router:Router){}
  
  rol = new Roles();
  usuarios = new Usuario();

  ngOnInit(): void {
    this.buscar();
  }

  buscar(){
    this.usuarios.idUsuario = Number(localStorage.getItem("idUsuario"));
    this,this.peticion.buscarU(this.usuarios).subscribe(data =>{
      console.log(JSON.stringify(data));
      this.usuarios=data;
    })
  }

  editar(){
    this.usuarios.rolId=this.rol;
    console.log(this.usuarios);
    this.peticion.editarU(this.usuarios).subscribe(data =>{
      Swal.fire({
        title: "EDITAR",
        text: JSON.stringify(data),
        icon:"success",
        draggable:true,
        showConfirmButton:false
      });
      this.router.navigate(['listarUsuario']);
    }, error=>{
       Swal.fire({
    icon: "error",
    title: "GUARDAR",
    text: JSON.stringify(error.error.mensaje),
    footer: '<a href="#">Why do I have this issue?</a>'
     });
    });
  }

  correo() {
  var nombre = this.usuarios.nombre?.toLowerCase() || '';
  var apellido = this.usuarios.app?.toLowerCase() || '';
  if (nombre && apellido) {
    this.usuarios.correo = `${nombre}.${apellido}@enucom.com.mx`;
  }
 }
}

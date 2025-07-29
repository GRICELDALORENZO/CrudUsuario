import { Component } from '@angular/core';
import { FormBuilder, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesWS } from '../../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { Roles } from '../../../Entidad/Roles';
import { Usuario } from '../../../Entidad/Usuario';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-usuario',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './guardar-usuario.html',
  styleUrl: './guardar-usuario.css'
})
export class GuardarUsuario {
  
  constructor(private peticion: PeticionesWS, private router:Router){}

  rol = new Roles();
  usuarios  =new Usuario();

guardarUsuario(){
  
    this.usuarios.rolId = this.rol;
    this.peticion.guardarU(this.usuarios).subscribe(data =>{
       Swal.fire({
      title: "GUARDAR",
      text:JSON.stringify(data),
      icon: "success",
      draggable: true
      
    });
    this.router.navigate(['listarUsuario']);
    }, error =>{
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


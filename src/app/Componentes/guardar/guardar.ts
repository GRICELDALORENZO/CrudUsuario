import { Component } from '@angular/core';
import { PeticionesWS } from '../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Roles } from '../../Entidad/Roles';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './guardar.html',
  styleUrl: './guardar.css'
})
export class Guardar {
  constructor(private peticion: PeticionesWS, private router:Router){}

  rol = new Roles();

  guardar(){
    this.peticion.guardar(this.rol).subscribe(data=>{
      console.log(JSON.stringify(data));
      Swal.fire({
      title: "GUARDAR",
      text:JSON.stringify(data),
      icon: "success",
      draggable: true
    });
    this.router.navigate(['listar']);
  }, error =>{
  Swal.fire({
  icon: "error",
  title: "GUARDAR",
  text: JSON.stringify(error.error.mensaje),
  footer: '<a href="#">Why do I have this issue?</a>'
});
})
 
}

}

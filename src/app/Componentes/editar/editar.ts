import { Component, OnInit } from '@angular/core';
import { PeticionesWS } from '../../Service/peticiones-ws';
import { Router } from '@angular/router';
import { Roles } from '../../Entidad/Roles';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar',
  imports: [FormsModule],
  templateUrl: './editar.html',
  styleUrl: './editar.css'
})
export class Editar implements OnInit {
  constructor(private peticion: PeticionesWS, private router:Router){}

  rol = new Roles();
  

  ngOnInit(): void {
    this.buscar();
  }

  buscar(){
    const idRol = Number(localStorage.getItem("idRol"));
    this.peticion.buscar(idRol).subscribe(data =>{
     console.log(JSON.stringify(data));
      this.rol = data;
    })
  }
editar(){
    console.log(this.rol);
    this.peticion.editar(this.rol).subscribe(data =>{
      Swal.fire({
        title: "EDITAR",
        text: JSON.stringify(data),
        icon: "success",
        draggable: true,
        showConfirmButton:false
      });
      this.router.navigate(['listar']);
    })
  }
  

}

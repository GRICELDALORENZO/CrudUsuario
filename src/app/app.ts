import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CrudUsuario');

  //instanciar al router
  constructor(private router:Router){}

  //crear un metodo que nos diriga al componente de listar
  listar(){
    this.router.navigate(['listar']);
  }

  listarUsuario(){
    this.router.navigate(['listarUsuario']);
  }
}

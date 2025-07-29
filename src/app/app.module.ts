import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- AQUI
import { GuardarUsuario } from './Componentes/Usuario/guardar-usuario/guardar-usuario';



@NgModule({
  declarations: [
    Component,
    GuardarUsuario, // tus otros componentes
  ],
  imports: [
    BrowserModule,
    FormsModule // <-- AQUI IMPORTADO
  ],
  providers: [],
  bootstrap: [Component]
})
export class AppModule {}
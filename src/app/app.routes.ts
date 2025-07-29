import { Routes } from '@angular/router';
import { Listar } from './Componentes/listar/listar';
import { ListarUsuario } from './Componentes/Usuario/listar-usuario/listar-usuario';
import { Guardar } from './Componentes/guardar/guardar';
import { GuardarUsuario } from './Componentes/Usuario/guardar-usuario/guardar-usuario';
import { Editar } from './Componentes/editar/editar';
import { EditarUsuario } from './Componentes/Usuario/editar-usuario/editar-usuario';

export const routes: Routes = [
//CREAR PATH PARA EL COMPONENTE LISTAR -

{path:'listar', component:Listar},
{path:'listarUsuario', component:ListarUsuario},
{path:'guardar', component:Guardar},
{path:'guardarUsuario', component:GuardarUsuario},
{path:'editar', component:Editar},
{path:'editarUsuario', component:EditarUsuario}


];

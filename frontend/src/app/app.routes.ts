import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RelatoListComponent } from './components/relato/relato-list/relato-list.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';
import { RelatoShowComponent } from './components/relato/relato-show/relato-show.component';
import { RelatoAddComponent } from './components/relato/relato-add/relato-add.component';
import { RelatoCreatedComponent } from './components/relato/relato-created/relato-created.component';

export const routes: Routes = [
  // Define las rutas
  { path: '', component: IndexComponent }, // Ruta por defecto
  { path: 'index', component: IndexComponent }, // Ruta para el componente IndexComponent
  { path: 'relatos', component: RelatoListComponent }, // Ruta para el componente IndexComponent con un parámetro
  { path: 'relato/:id', component: RelatoShowComponent }, // Ruta para el componente RelatoShowComponent con un parámetro
  { path: 'relatoAdd', component: RelatoAddComponent }, // Ruta para el componente RelatoAddComponent
  { path: 'relatosUsuario', component: RelatoCreatedComponent }, // Ruta para el componente RelatoCreatedComponent
  { path: 'registro', component: RegistroComponent },
  { path: 'login', component: LoginComponent }, // Ruta para el componente LoginComponent
  { path: '**', redirectTo: '' }, // Ruta por defecto si no se encuentra la ruta
];

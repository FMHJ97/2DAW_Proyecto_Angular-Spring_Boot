import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RelatoListComponent } from './components/relato/relato-list/relato-list.component';

export const routes: Routes = [
  // Define las rutas
  {path: '', component: IndexComponent}, // Ruta por defecto
  {path: 'index', component: IndexComponent}, // Ruta para el componente IndexComponent
  {path: 'relatos', component: RelatoListComponent}, // Ruta para el componente IndexComponent con un parámetro
  {path: '**', redirectTo: ''} // Ruta por defecto si no se encuentra la ruta
];

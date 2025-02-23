import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RelatoListComponent } from './components/relato/relato-list/relato-list.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';
import { RelatoShowComponent } from './components/relato/relato-show/relato-show.component';
import { RelatoAddComponent } from './components/relato/relato-add/relato-add.component';
import { RelatoCreatedComponent } from './components/relato/relato-created/relato-created.component';
import { AdminUsuarioComponent } from './components/admin/admin-usuario/admin-usuario.component';
import { AdminRelatoComponent } from './components/admin/admin-relato/admin-relato.component';
import { AuthGuard } from './guards/auth.guard';
import { UserGuard } from './guards/user.guard';
import { AdminGuard } from './guards/admin.guard';
import { NoAuthGuard } from './guards/no-auth.guard';
import { PerfilComponent } from './components/auth/perfil/perfil.component';
import { RelatoEditComponent } from './components/relato/relato-edit/relato-edit.component';

export const routes: Routes = [
  // Define las rutas
  { path: '', component: IndexComponent }, // Ruta por defecto
  { path: 'index', component: IndexComponent }, // Ruta para el componente IndexComponent
  { path: 'relatos', component: RelatoListComponent }, // Ruta para el componente IndexComponent con un parámetro
  { path: 'relato/:id', component: RelatoShowComponent, canActivate: [AuthGuard] }, // Ruta para el componente RelatoShowComponent con un parámetro
  { path: 'relatoAdd', component: RelatoAddComponent, canActivate: [UserGuard] }, // Ruta para el componente RelatoAddComponent
  { path: 'relatosUsuario', component: RelatoCreatedComponent, canActivate: [UserGuard] }, // Ruta para el componente RelatoCreatedComponent
  { path: 'relatoEdit/:id', component: RelatoEditComponent }, //
  { path: 'adminUsuarios', component: AdminUsuarioComponent, canActivate: [AdminGuard] }, // Ruta para el componente AdminUsuarioComponent
  { path: 'adminRelatos', component: AdminRelatoComponent, canActivate: [AdminGuard] }, // Ruta para el componente AdminRelatoComponent
  { path: 'registro', component: RegistroComponent, canActivate: [NoAuthGuard] }, // Ruta para el componente RegistroComponent
  { path: 'login', component: LoginComponent, canActivate: [NoAuthGuard] }, // Ruta para el componente LoginComponent
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] }, // Ruta para el componente PerfilComponent
  { path: '**', redirectTo: '' }, // Ruta por defecto si no se encuentra la ruta
];

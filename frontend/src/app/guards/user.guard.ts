import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ApiService } from '../services/api.service';

// Este guard permitirá el acceso solo a los usuarios autenticados que no sean administradores.
export const UserGuard: CanActivateFn = (route, state) => {
  const apiService = inject(ApiService);
  const router = inject(Router);

  const user = apiService.getUserFromLocalStorage(); // Obtiene el usuario autenticado

  if (!user) {
    router.navigate(['/login']); // Si no está autenticado, redirige al login
    return false;
  }

  if (user.rol === 'admin') {
    router.navigate(['/']); // Si es admin, lo redirige a inicio
    return false;
  }

  return true; // Si es usuario normal, le permite acceder
};

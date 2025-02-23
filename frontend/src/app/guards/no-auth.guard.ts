import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ApiService } from '../services/api.service';

// Este guard permitirá el acceso solo a los usuarios no autenticados.
export const NoAuthGuard: CanActivateFn = (route, state) => {
  const apiService = inject(ApiService);
  const router = inject(Router);

  const user = apiService.getUserFromLocalStorage(); // Verifica si hay usuario autenticado

  if (user) {
    router.navigate(['/']); // Redirige a la página principal
    return false;
  }
  return true;
};

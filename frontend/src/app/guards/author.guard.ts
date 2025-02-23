import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';

// Este guard permitirá el acceso solo al autor del relato.
export const AuthorGuard: CanActivateFn = (route, state) => {
  const apiService = inject(ApiService);
  const router = inject(Router);

  const user = apiService.getUserFromLocalStorage();
  const idRelato = route.paramMap.get('id'); // ID del relato desde la URL

  // Verifica si hay usuario autenticado o ID de relato
  // Si no, redirige a la página de inicio de sesión
  if (!user || !idRelato) {
    router.navigate(['/login']);
    return false;
  }

  // Verifica si el usuario autenticado es el autor del relato
  return new Promise<boolean>((resolve) => {
    apiService.getRelatoById(idRelato).subscribe({
      next: (relato) => {
        // Si el autor del relato es el usuario autenticado, permite el acceso
        if (relato.id_usuario === user.id) {
          resolve(true);
        } else
        // Si el autor del relato no es el usuario autenticado, redirige a la página de relatos
        {
          router.navigate(['/relatos']);
          resolve(false);
        }
      },
      error: () => {
        router.navigate(['/relatos']);
        resolve(false);
      }
    });
  });
};

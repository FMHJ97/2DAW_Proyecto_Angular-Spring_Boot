import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  user: any = null; // Para almacenar los datos del usuario
  isAuthenticated: boolean = false; // Para verificar si el usuario está autenticado

  constructor(private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    // Solo en el navegador
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('token');
      this.isAuthenticated = !!token; // Si el token existe, el usuario está autenticado

      if (this.isAuthenticated) {
        // Si está autenticado, obtenemos los datos del usuario
        this.apiService.getUserData().subscribe({
          next: (response) => {
            console.log('Datos del usuario:', response);
            this.user = response; // Guardamos los datos del usuario
          },
          error: (error) => {
            console.error('Error al obtener los datos del usuario:', error);
            this.logout(); // Si hay un error, desconectamos al usuario
          },
        });
      }
    }
  }

  logout(): void {
    // Eliminar token y datos del localStorage
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    // Redirigir al login
    this.router.navigate(['/login']);
  }
}

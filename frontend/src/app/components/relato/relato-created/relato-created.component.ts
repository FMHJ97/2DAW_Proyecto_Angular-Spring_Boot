import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';
import { filter, take } from 'rxjs/operators';

@Component({
  selector: 'app-relato-created',
  standalone: true,
  imports: [RouterModule, CommonModule, NgxPaginationModule],
  templateUrl: './relato-created.component.html',
  styleUrl: './relato-created.component.css'
})
export class RelatoCreatedComponent implements OnInit {
  relatos: any[] = [];
  message: string | null = null;
  paginaActual: number = 1;
  itemsPorPagina: number = 6;
  user: any = null;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.cargarRelatos();
  }

  cargarRelatos() {
    this.apiService.user$.pipe(
      filter(user => !!user), // Asegurar que el usuario está definido
      take(1) // Tomar solo la primera emisión
    ).subscribe(user => {
      this.user = user;
      // Obtener relatos del usuario actual.
      this.apiService.getRelatosByUsuario(user.id).subscribe({
        next: (data: any[]) => {
          console.log('Relatos del usuario:', data);
          this.relatos = data;
          this.message = data.length ? null : 'No tienes relatos aún.';
        },
        error: (err: any) => {
          console.error('Error al cargar los relatos:', err);
          this.message = 'Error al cargar los relatos.';
        },
      });
    });
  }
}

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
      filter(user => !!user),
      take(1)
    ).subscribe(user => {
      this.user = user;
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

  eliminarRelato(id: string) {
    if (!confirm('¿Estás seguro de que quieres eliminar este relato?')) return;

    this.apiService.deleteRelato(id).subscribe({
      next: (response) => {
        if (response.result === 'ok') {
          // Elimina el relato de la lista de relatos
          this.relatos = this.relatos.filter(relato => relato.id !== id);
          alert('Relato eliminado correctamente');
        } else {
          alert('No se pudo eliminar el relato.');
        }
      },
      error: (err) => {
        console.error('Error al eliminar el relato:', err);
        alert('Hubo un error al eliminar el relato.');
      }
    });
  }
}

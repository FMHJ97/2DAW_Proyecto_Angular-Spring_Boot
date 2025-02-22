import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';
import { filter, take } from 'rxjs/operators';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../../../components/relato/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-admin-relato',
  standalone: true,
  imports: [RouterModule, CommonModule, NgxPaginationModule, MatDialogModule],
  templateUrl: './admin-relato.component.html',
  styleUrl: './admin-relato.component.css'
})
export class AdminRelatoComponent implements OnInit {
  relatos: any[] = [];
  message: string | null = null;
  paginaActual: number = 1;
  itemsPorPagina: number = 6;

  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  ngOnInit() {
    this.cargarRelatos();
  }

  cargarRelatos() {
    this.apiService.getRelatosByFecha().subscribe({
      next: (data: any[]) => {
        this.relatos = data;
        this.message = data.length ? null : 'No hay relatos aún.';
      },
      error: (err: any) => {
        console.error('Error al cargar los relatos:', err);
        this.message = 'Error al cargar los relatos.';
      }
    });
  }

  abrirModalEliminar(relato: any) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '350px',
      data: relato
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.confirmarEliminar(relato.id);
      }
    });
  }

  confirmarEliminar(id: string) {
    this.apiService.deleteRelato(id).subscribe({
      next: () => {
        this.relatos = this.relatos.filter(r => r.id !== id);
      },
      error: (err) => console.error('Error al eliminar el relato:', err)
    });
  }
}

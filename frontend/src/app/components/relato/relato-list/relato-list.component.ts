import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-relato-list',
  standalone: true,
  imports: [RouterModule, CommonModule, NgxPaginationModule],
  templateUrl: './relato-list.component.html',
  styleUrl: './relato-list.component.css'
})
export class RelatoListComponent implements OnInit {
  relatos: any[] = [];
  message: string | null = null;
  paginaActual: number = 1;
  itemsPorPagina: number = 6;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.cargarRelatos();
  }

  cargarRelatos() {
    this.apiService.getRelatosByFecha().subscribe({
      next: (data: any[]) => {
        this.relatos = data;
      },
      error: (err: any) => {
        console.error('Error al cargar los relatos:', err);
      },
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-relato-list',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './relato-list.component.html',
  styleUrl: './relato-list.component.css'
})
export class RelatoListComponent implements OnInit {
  relatos: any[] = [];
  message: string | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    // Carga los relatos
    this.cargarRelatos();
  }

  // Método para cargar los relatos desde el backend
  cargarRelatos() {
    // Llama al método getRelatosByFecha del servicio API
    this.apiService.getRelatosByFecha().subscribe({
      next: (data: any[]) => {
        this.relatos = data; // Asigna los datos a la variable relatos
      },
      error: (err: any) => {
        console.error('Error al cargar los relatos:', err);
      },
    });
  }



}

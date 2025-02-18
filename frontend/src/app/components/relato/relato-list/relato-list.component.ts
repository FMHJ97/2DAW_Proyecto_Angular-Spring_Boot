import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-relato-list',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './relato-list.component.html',
  styleUrl: './relato-list.component.css'
})
export class RelatoListComponent implements OnInit {
  relatos: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.cargarRelatos();
  }

  // Método para cargar los relatos desde el backend
  cargarRelatos() {
    this.apiService.getRelatos().subscribe({
      next: (data: any[]) => {
        this.relatos = data; // Asigna los datos a la variable relatos
      },
      error: (err: any) => {
        console.error('Error al cargar los relatos:', err);
      },
    });
  }



}

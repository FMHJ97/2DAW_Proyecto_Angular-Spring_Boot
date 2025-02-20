import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { timestamp } from 'rxjs';

@Component({
  selector: 'app-relato-add',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './relato-add.component.html',
  styleUrl: './relato-add.component.css'
})
export class RelatoAddComponent implements OnInit {
  generos: any[] = [];
  relatoForm: FormGroup;
  generosSeleccionados: number[] = [];

  constructor(private fb: FormBuilder, private apiService: ApiService, private router: Router) {
    this.relatoForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      resumen: ['', Validators.required],
      contenido: ['', Validators.required],
      portadaUrl: [''],
      id_usuario: [1, Validators.required] // Cambia esto dinámicamente según el usuario logueado
    });
  }

  ngOnInit(): void {
    this.apiService.getGeneros().subscribe({
      next: (data: any) => {
        this.generos = data;
      },
      error: (err: any) => {
        console.error('Error al obtener los géneros:', err);
      }
    });
  }

  onGeneroChange(event: any, id_genero: number) {
    if (event.target.checked) {
      this.generosSeleccionados.push(id_genero);
    } else {
      this.generosSeleccionados = this.generosSeleccionados.filter(id => id !== id_genero);
    }
  }

  onSubmit() {
    if (this.relatoForm.valid) {
      const now = new Date();
      const fechaPublicacion = new Date(now.getTime() - now.getTimezoneOffset() * 60000).toISOString(); // Agrega la fecha actual

      const relatoData = {
        ...this.relatoForm.value,
        fechaPublicacion: fechaPublicacion // Agrega la fecha de publicación
      };

      this.apiService.insertarRelato(relatoData).subscribe({
        next: (data: any) => {
          console.log('Relato insertado:', data);
          const id_relato = data.id; // Verificar si aquí se recibe el ID del relato

          if (id_relato) {
            this.insertarGeneros(id_relato);
            // Redirigir a la lista de relatos
            this.router.navigate(['/relatos']);
          } else {
            console.error('No se recibió ID del relato insertado');
          }
        },
        error: (err: any) => {
          console.error('Error al insertar el relato:', err);
        }
      });
    }
  }

  insertarGeneros(id_relato: number) {
    if (this.generosSeleccionados.length > 0) {
      const generosData = this.generosSeleccionados.map(id_genero => ({
        id_relato,
        id_genero
      }));

      // Enviar cada género por separado para evitar errores en la API
      generosData.forEach((genero) => {
        this.apiService.insertarGenerosRelato(genero).subscribe({
          next: () => console.log(`Género ${genero.id_genero} insertado`),
          error: (err: any) => console.error('Error al insertar género:', err)
        });
      });
    }
  }
}

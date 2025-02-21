import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-relato-add',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule, QuillModule],
  templateUrl: './relato-add.component.html',
  styleUrl: './relato-add.component.css'
})
export class RelatoAddComponent implements OnInit {
  generos: any[] = [];
  relatoForm: FormGroup;
  generosSeleccionados: number[] = [];
  user: any = null;
  generoError: string | null = null; // Mensaje de error

  quillModules = {
    toolbar: [
      ['bold', 'italic', 'underline'],
      ['clean']
    ]
  };

  constructor(private fb: FormBuilder, private apiService: ApiService, private router: Router) {
    this.relatoForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      resumen: ['', Validators.required],
      contenido: ['', Validators.required],
      id_usuario: [null, Validators.required] // Se actualizará dinámicamente
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

    this.apiService.user$.subscribe(user => {
      if (user) {
        this.user = user;
        this.relatoForm.patchValue({ id_usuario: user.id });
      }
    });
  }

  onGeneroChange(event: any, id_genero: number) {
    if (event.target.checked) {
      if (this.generosSeleccionados.length < 4) {
        this.generosSeleccionados.push(id_genero);
      } else {
        event.target.checked = false;
        this.generoError = 'Solo puedes seleccionar hasta 4 géneros.';
      }
    } else {
      this.generosSeleccionados = this.generosSeleccionados.filter(id => id !== id_genero);
    }

    this.validarGeneros();
  }

  validarGeneros() {
    if (this.generosSeleccionados.length < 1) {
      this.generoError = 'Debes seleccionar al menos un género.';
    } else {
      this.generoError = null;
    }
  }

  onSubmit() {
    this.validarGeneros();

    if (this.relatoForm.valid && !this.generoError) {
      if (!this.user) {
        console.error('No hay un usuario autenticado. No se puede crear el relato.');
        return;
      }

      const now = new Date();
      const fechaPublicacion = new Date(now.getTime() - now.getTimezoneOffset() * 60000).toISOString();

      // Convertir entidades HTML a caracteres normales
      const contenidoLimpio = this.decodeHtmlEntities(this.relatoForm.value.contenido);

      const relatoData = {
        ...this.relatoForm.value,
        contenido: contenidoLimpio, // Guardar contenido limpio en BD
        fechaPublicacion
      };

      this.apiService.insertarRelato(relatoData).subscribe({
        next: (data: any) => {
          console.log('Relato insertado:', data);
          const id_relato = data.id;

          if (id_relato) {
            this.insertarGeneros(id_relato);
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

  // Método para convertir entidades HTML en caracteres normales
  decodeHtmlEntities(text: string): string {
    const textarea = document.createElement("textarea");
    textarea.innerHTML = text;
    return textarea.value;
  }

  insertarGeneros(id_relato: number) {
    if (this.generosSeleccionados.length > 0) {
      const generosData = this.generosSeleccionados.map(id_genero => ({
        id_relato,
        id_genero
      }));

      generosData.forEach((genero) => {
        this.apiService.insertarGenerosRelato(genero).subscribe({
          next: () => console.log(`Género ${genero.id_genero} insertado`),
          error: (err: any) => console.error('Error al insertar género:', err)
        });
      });
    }
  }
}

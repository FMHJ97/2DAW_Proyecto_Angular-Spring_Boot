import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-relato-edit',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule, QuillModule],
  templateUrl: './relato-edit.component.html',
  styleUrls: ['./relato-edit.component.css']
})
export class RelatoEditComponent implements OnInit {
  relatoForm: FormGroup;
  generos: any[] = [];
  generosSeleccionados: number[] = [];
  generoError: string | null = null;
  user: any = null;
  relatoId!: number;
  now = new Date();

  quillModules = {
    toolbar: [['bold', 'italic', 'underline'], ['clean']]
  };

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.relatoForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      resumen: ['', Validators.required],
      contenido: ['', Validators.required],
      id_usuario: [null, Validators.required],
      fechaPublicacion: [
        new Date(this.now.getTime() - this.now.getTimezoneOffset() * 60000).toISOString(),
        Validators.required,
      ],
    });
  }

  ngOnInit(): void {
    this.relatoId = Number(this.route.snapshot.paramMap.get('id'));

    // Obtener la lista de géneros
    this.apiService.getGeneros().subscribe({
      next: (data: any) => (this.generos = data),
      error: (err: any) => console.error('Error al obtener géneros:', err),
    });

    // Obtener datos del usuario
    this.apiService.user$.subscribe((user) => {
      if (user) {
        this.user = user;
        this.relatoForm.patchValue({ id_usuario: user.id });
      }
    });

    // Obtener el relato por ID
    this.apiService.getRelatoById(this.relatoId.toString()).subscribe({
      next: (relato: any) => {
        console.log('Relato:', relato);
        relato.fechaPublicacion =
          relato.fechaPublicacion ||
          new Date(this.now.getTime() - this.now.getTimezoneOffset() * 60000).toISOString();
        this.relatoForm.patchValue(relato);
        // Establecer los géneros seleccionados (IDs)
        this.generosSeleccionados = relato.generos.map((g: any) => g.id);
      },
      error: (err: any) => console.error('Error al obtener el relato:', err),
    });
  }

  onGeneroChange(event: any, id_genero: number) {
    if (event.target.checked) {
      if (this.generosSeleccionados.length < 4) {
        this.generosSeleccionados.push(id_genero);
        console.log('Generos seleccionados:', this.generosSeleccionados);
      } else {
        event.target.checked = false;
        this.generoError = 'Solo puedes seleccionar hasta 4 géneros.';
      }
    } else {
      this.generosSeleccionados = this.generosSeleccionados.filter((id) => id !== id_genero);
    }
    this.validarGeneros();
  }

  validarGeneros() {
    this.generoError =
      this.generosSeleccionados.length < 1 ? 'Debes seleccionar al menos un género.' : null;
  }

  onSubmit() {
    this.validarGeneros();
    if (this.relatoForm.valid && !this.generoError) {
      // Eliminar géneros asociados al relato antes de actualizar
      this.apiService.deleteGenerosRelato(this.relatoId.toString()).subscribe({
        next: () => this.actualizarRelato(),
        error: (err: any) => console.error('Error al eliminar géneros:', err),
      });
    }
  }

  actualizarRelato() {

    // Convertir entidades HTML a caracteres normales
    const contenidoLimpio = this.decodeHtmlEntities(this.relatoForm.value.contenido);

    const relatoData = {
      ...this.relatoForm.value,
      id: this.relatoId,
      fechaPublicacion: new Date(
        this.now.getTime() - this.now.getTimezoneOffset() * 60000
      ).toISOString(),
      contenido: contenidoLimpio,
    };
    // Actualizar el relato
    this.apiService.updateRelato(relatoData).subscribe({
      next: () => {
        this.insertarGeneros();
        // Redirigir al listado de relatos del usuario.
        this.router.navigate(['/relatosUsuario']);
      },
      error: (err: any) => console.error('Error al actualizar el relato:', err),
    });
  }

  insertarGeneros() {
    if (this.generosSeleccionados.length > 0) {
      const id_relato = this.relatoId;
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


  // Método para convertir entidades HTML en caracteres normales
  decodeHtmlEntities(text: string): string {
    const textarea = document.createElement("textarea");
    textarea.innerHTML = text;
    return textarea.value;
  }
}

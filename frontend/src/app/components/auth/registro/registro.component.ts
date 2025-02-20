import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent {
  registroForm: FormGroup;
  mensaje: string = '';

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.registroForm = this.fb.group({
      nombre: [''],
      apellidos: [''],
      usuario: [''],
      password: [''],
      email: [''],
      fechaNacimiento: [''],
      pais: [''],
      sexo: [''],
    });
  }

  onSubmit() {
    if (this.registroForm.valid) {
      // Construir la estructura correcta para la API
      const usuarioData = {
        nombre: this.registroForm.value.nombre,
        apellidos: this.registroForm.value.apellidos,
        usuario: this.registroForm.value.usuario,
        password: this.registroForm.value.password,
        email: this.registroForm.value.email,
        fechaNacimiento: this.registroForm.value.fechaNacimiento,
        pais: this.registroForm.value.pais,
        sexo: this.registroForm.value.sexo,
        rol: 'usuario', // Aquí asignamos el rol como "usuario"
      };

      // Llamar al servicio para registrar el usuario
      this.apiService.registrarUsuario(usuarioData).subscribe({
        next: (response) => {
          console.log('Usuario registrado con éxito:', response);
          this.mensaje = 'Usuario registrado exitosamente.';
          this.registroForm.reset(); // Limpia el formulario
          // Redirigir al usuario a la página de login
          window.location.href = '/login';
        },
        error: (error) => {
          console.error('Error en el registro:', error);
          this.mensaje = 'Error al registrar el usuario.';
        },
      });
    } else {
      this.mensaje = 'Por favor, completa todos los campos.';
    }
  }
}

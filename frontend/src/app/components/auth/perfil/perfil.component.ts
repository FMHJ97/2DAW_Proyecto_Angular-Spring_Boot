import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent implements OnInit {
  perfilForm: FormGroup;
  mensaje: string = '';

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private router: Router
  ) {
    this.perfilForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(2)]],
      apellidos: ['', [Validators.required, Validators.minLength(2)]],
      fecha_nacimiento: ['', Validators.required],
      pais: ['', Validators.required],
      sexo: ['', Validators.required],
      password: [''],
      confirmPassword: ['']
    }, { validator: this.passwordsMatch }); // Custom validator for matching passwords
  }

  ngOnInit(): void {
    // Load authenticated user data
    const usuario = this.apiService.getUserFromLocalStorage();
    if (usuario) {
      this.perfilForm.patchValue({
        nombre: usuario.nombre,
        apellidos: usuario.apellidos,
        fecha_nacimiento: usuario.fecha_nacimiento,
        pais: usuario.pais,
        sexo: usuario.sexo,
      });
    }

    // Disable confirm password initially
    this.perfilForm.get('confirmPassword')?.disable();
  }

  // Custom validation to check if passwords match
  passwordsMatch(group: FormGroup): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { 'passwordsDoNotMatch': true };
  }

  // Enable or disable confirm password field based on password input
  onPasswordChange() {
    const passwordControl = this.perfilForm.get('password');
    const confirmPasswordControl = this.perfilForm.get('confirmPassword');

    if (passwordControl?.value) {
      confirmPasswordControl?.enable(); // Enable confirm password field
    } else {
      confirmPasswordControl?.disable(); // Disable confirm password field
    }
  }

  onSubmit() {
    if (this.perfilForm.invalid) {
      this.mensaje = 'Por favor, completa todos los campos correctamente.';
      return;
    }

    const usuarioData = { ...this.perfilForm.value };

    // Add authenticated user's data
    const usuario = this.apiService.getUserFromLocalStorage();
    if (usuario) {
      usuarioData.id = usuario.id;
      usuarioData.usuario = usuario.usuario;
      usuarioData.email = usuario.email;
      usuarioData.rol = usuario.rol;

      // Only include password if it's not empty
      if (usuarioData.password) {
        usuarioData.password = usuarioData.password;
      } else {
        delete usuarioData.password; // Remove password if not updated
      }
    }

    this.apiService.updateUsuario(usuarioData).subscribe({
      next: (response) => {
        console.log('Datos actualizados con éxito:', response);
        this.mensaje = 'Datos actualizados correctamente.';

        // Actualizar el LocalStorage con los nuevos datos
        localStorage.setItem('user', JSON.stringify(usuarioData));

        // Opcional: Actualizar el estado del usuario en el servicio
        this.apiService.saveUserData(localStorage.getItem('jwt') || '', usuarioData);
      },
      error: (error) => {
        console.error('Error al actualizar los datos:', error);
        this.mensaje = 'Error al actualizar los datos.';
      },
    });
  }

}

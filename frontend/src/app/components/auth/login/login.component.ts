import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true, // Indicamos que este componente es standalone
  imports: [CommonModule, ReactiveFormsModule], // Importamos ReactiveFormsModule y CommonModule para que funcione
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      usuario: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      this.apiService.login(loginData).subscribe({
        next: (response) => {
          if (response.result === 'ok') {
            console.log('Usuario autenticado con éxito:', response);
            localStorage.setItem('token', response.jwt); // Guardar el token JWT
            localStorage.setItem('user', JSON.stringify(response.user)); // Guardar los datos del usuario
            this.apiService.getUserData().subscribe({
              next: (response) => {
                console.log('Datos del usuario:', response);
                var user = response; // Guardamos los datos del usuario
              },
            });
            // Redirigir a una página protegida (por ejemplo, un dashboard)
            this.router.navigate(['/']);
          } else {
            alert('Error en la autenticación: ' + response.msg);
          }
        },
        error: (error) => {
          console.error('Error de autenticación:', error);
          alert('Error al autenticar el usuario');
        },
      });
    }
  }
}

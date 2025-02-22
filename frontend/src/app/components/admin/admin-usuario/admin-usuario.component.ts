import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AdminDeleteDialogComponent } from '../admin-delete-dialog/admin-delete-dialog.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NgxPaginationModule, MatDialogModule],
  templateUrl: './admin-usuario.component.html',
  styleUrl: './admin-usuario.component.css'
})
export class AdminUsuarioComponent implements OnInit {
  usuarios: any[] = [];
  paginaActual: number = 1;
  itemsPorPagina: number = 10;
  usuarioActual: any = null;

  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  ngOnInit() {
    this.apiService.user$.subscribe(user => {
      this.usuarioActual = user;
      this.cargarUsuarios();
    });
  }

  cargarUsuarios() {
    this.apiService.getUsuarios().subscribe({
      next: (data: any[]) => {
        this.usuarios = data.filter(u => u.id !== this.usuarioActual?.id); // Excluir usuario actual
      },
      error: (err: any) => {
        console.error('Error al cargar los usuarios:', err);
      },
    });
  }

  abrirModalEliminar(usuario: any) {
    const dialogRef = this.dialog.open(AdminDeleteDialogComponent, {
      width: '350px',
      data: usuario
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.confirmarEliminar(usuario.id);
      }
    });
  }

  confirmarEliminar(id: string) {
    this.apiService.deleteUsuario(id).subscribe({
      next: () => {
        this.usuarios = this.usuarios.filter(u => u.id !== id);
      },
      error: (err) => console.error('Error al eliminar el usuario:', err)
    });
  }

  actualizarRol(usuario: any) {
    // Alternar el rol localmente antes de la petición
    const nuevoRol = usuario.rol === 'admin' ? 'usuario' : 'admin';

    // Datos actualizados
    const datosActualizados = {
      ...usuario,  // Copiamos todos los datos del usuario
      rol: nuevoRol,  // Actualizamos solo el rol
    };

    console.log('Datos actualizados:', datosActualizados);

    // Llamada a la API para actualizar el usuario
    this.apiService.updateUsuario(datosActualizados).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);

        if (response.result === 'ok') {
          // Actualizamos el rol en el frontend manualmente
          usuario.rol = nuevoRol;
          console.log(`Rol de ${usuario.nombre} actualizado a ${usuario.rol}`);
        } else {
          console.error('Error en la respuesta del servidor:', response.message);
        }
      },
      error: (err) => {
        console.error('Error al actualizar el rol:', err);
      },
    });
  }

}

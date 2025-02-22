import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delete-dialog',
  standalone: true,
  template: `
    <div class="modal-content shadow-lg p-4">
      <div class="modal-header bg-danger text-white ps-3">
        <h4 class="modal-title"><i class="bi bi-exclamation-triangle-fill"></i> Confirmar Eliminación</h4>
      </div>
      <div class="modal-body text-center">
        <p class="fs-4">¿Estás seguro de que deseas eliminar el relato <strong class="text-danger">"{{ data.titulo }}"</strong>?</p>
        <p class="text-muted">Esta acción no se puede deshacer.</p>
      </div>
      <div class="modal-footer d-flex justify-content-between">
        <button class="btn btn-lg btn-secondary" (click)="close()">
          <i class="bi bi-x-circle"></i> Cancelar
        </button>
        <button class="btn btn-lg btn-danger" (click)="confirm()">
          <i class="bi bi-trash"></i> Eliminar
        </button>
      </div>
    </div>
  `,
  styles: [`
    .modal-content {
      max-width: 550px;
      border-radius: 12px;
      overflow: hidden;
    }
    .modal-header {
      border-bottom: none;
      font-size: 1.25rem;
    }
    .modal-body p {
      margin-bottom: 10px;
    }
    .modal-footer {
      border-top: none;
      padding-top: 10px;
    }
    .btn-lg {
      font-size: 1rem;
      padding: 10px 20px;
    }
  `],
  imports: [CommonModule]
})
export class DeleteDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  close() {
    this.dialogRef.close(false);
  }

  confirm() {
    this.dialogRef.close(true);
  }
}

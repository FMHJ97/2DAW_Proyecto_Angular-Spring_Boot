import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-relato-show',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './relato-show.component.html',
  styleUrl: './relato-show.component.css'
})
export class RelatoShowComponent implements OnInit {
  relato: any = {};
  selectedRelatoId: string | null = null;

  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.cargarRelato();
  }

  // Método para obtener un relato por ID
  cargarRelato(): void {
    const relatoId = this.route.snapshot.paramMap.get('id');
    if (relatoId) {
      this.selectedRelatoId = relatoId;
      this.apiService.getRelatoById(relatoId).subscribe({
        next: (data: any) => {
          this.relato = data;
        },
        error: (err: any) => {
          console.error('Error al cargar el relato:', err);
        },
      },
    );
    }
  }

}

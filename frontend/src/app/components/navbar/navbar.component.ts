import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  user: any = null;
  isAuthenticated: boolean = false;

  constructor(private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.user$.subscribe(user => {
      this.user = user;
      this.isAuthenticated = !!user; // Se actualiza automáticamente al iniciar/cerrar sesión
    });
  }

  logout(): void {
    this.apiService.logout();
    this.router.navigate(['/login']);
  }
}

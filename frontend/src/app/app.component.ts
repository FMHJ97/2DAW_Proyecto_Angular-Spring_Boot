import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router'; // Import RouterModule for routing
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule here
import { NavbarComponent } from './components/navbar/navbar.component';
import { ApiService } from './services/api.service'; // Import ApiService here
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HttpClientModule,
    NavbarComponent,
    RouterModule,
    ReactiveFormsModule,
  ], // Add RouterOutlet, HttpClientModule, NavbarComponent, and RouterModule to imports
  providers: [ApiService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'StoryHaven';
}

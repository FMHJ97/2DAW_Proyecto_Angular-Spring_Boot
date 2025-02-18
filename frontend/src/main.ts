import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app/app.routes'; // Importa las rutas

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes), // Configura las rutas
    provideHttpClient() // Proporciona HttpClient
  ]
});

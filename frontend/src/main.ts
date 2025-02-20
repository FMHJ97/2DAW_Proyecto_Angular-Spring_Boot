import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient } from '@angular/common/http';
import { routes } from './app/app.routes'; // Importa las rutas
import { JwtInterceptor } from './app/interceptors/jwt.interceptor';

bootstrapApplication(AppComponent, {
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    provideRouter(routes), // Configura las rutas
    provideHttpClient(), // Proporciona HttpClient
  ],
});

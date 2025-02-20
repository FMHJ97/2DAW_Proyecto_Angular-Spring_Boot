import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient } from '@angular/common/http';
import { routes } from './app/app.routes'; // Importa las rutas
import { JwtInterceptor } from './app/interceptors/jwt.interceptor';
import { registerLocaleData } from '@angular/common';
import es from '@angular/common/locales/es';
import { LOCALE_ID } from '@angular/core';

// Registrar el idioma español
registerLocaleData(es);

bootstrapApplication(AppComponent, {
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    provideRouter(routes), // Configura las rutas
    provideHttpClient(), // Proporciona HttpClient
    { provide: LOCALE_ID, useValue: 'es' }, // Configura el idioma de la aplicación a español
  ],
});

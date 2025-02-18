import { Injectable } from '@angular/core'; // Importante para crear servicios
import { HttpClient } from '@angular/common/http'; // Importante para realizar peticiones HTTP
import { Observable } from 'rxjs'; // Importante para manejar las respuestas asíncronas

@Injectable({
  providedIn: 'root', // Indica que el servicio estará disponible en toda la aplicación
})
export class ApiService {
  private baseUrl = 'http://localhost:8080'; // Ajusta la URL según tu backend

  constructor(private http: HttpClient) {} // Inyecta el servicio HttpClient

  // Método para obtener el listado de relatos
  getRelatos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/relatos/all`);
  }
}

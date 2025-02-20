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
  getRelatosByFecha(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/relatos/all2`);
  }

  // Método para obtener un relato por ID
  getRelatoById(id: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/relatos/get`, { id });
  }

  // Método para insertar un nuevo relato
  insertarRelato(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/relatos/new`, datos);
  }

  // Método para obtener el listado de géneros
  getGeneros(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/generos/all`);
  }

  // Método para insertar géneros a un relato.
  insertarGenerosRelato(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/relato-generos/new`, datos);
  }

  registrarUsuario(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios/new`, datos);
  }

  // Método para hacer login y obtener el token
  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuarios/auth`, credentials); // Cambia la URL de tu API
  }

  // Método para obtener los datos del usuario autenticado
  getUserData(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/usuarios/who`); // La ruta para obtener los datos del usuario autenticado
  }
}

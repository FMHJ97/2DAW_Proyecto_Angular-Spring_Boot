import { Injectable } from '@angular/core'; // Importante para crear servicios
import { HttpClient, HttpHeaders } from '@angular/common/http'; // Importante para realizar peticiones HTTP
import { BehaviorSubject, Observable, tap, throwError } from 'rxjs'; // Importante para manejar las respuestas asíncronas

@Injectable({
  providedIn: 'root', // Indica que el servicio estará disponible en toda la aplicación
})
export class ApiService {
  private baseUrl = 'http://localhost:8080'; // Ajusta la URL según tu backend

  private userSubject = new BehaviorSubject<any>(null); // BehaviorSubject para obtener usuario en tiempo real
  user$ = this.userSubject.asObservable(); // Observable para obtener usuario en tiempo real

  constructor(private http: HttpClient) {
    if (typeof window !== 'undefined') { // Asegura que solo se ejecuta en el navegador
      this.userSubject.next(this.getUserFromLocalStorage());
    }
  } // Inyecta el servicio HttpClient

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

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuarios/auth`, credentials).pipe(
      tap(response => {
        if (response.result === 'ok') {
          localStorage.setItem('jwt', response.jwt);
          this.getAuthenticatedUser().subscribe(user => {
            this.saveUserData(response.jwt, user);
          });
        }
      })
    );
  }

  getAuthenticatedUser(): Observable<any> {
    const token = localStorage.getItem('jwt');
    if (!token) {
      return throwError(() => new Error('No hay token disponible'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.baseUrl}/usuarios/who`, { headers }).pipe(
      tap(user => this.userSubject.next(user)) // Actualiza el usuario en tiempo real
    );
  }

  saveUserData(token: string, user: any): void {
    localStorage.setItem('jwt', token);
    localStorage.setItem('user', JSON.stringify(user));
    this.userSubject.next(user); // Notifica a los componentes suscritos
  }

  getUserFromLocalStorage(): any {
    if (typeof window !== 'undefined' && localStorage) {
      const user = localStorage.getItem('user');
      return user ? JSON.parse(user) : null;
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    this.userSubject.next(null); // Notifica a los componentes que ya no hay usuario
  }
}

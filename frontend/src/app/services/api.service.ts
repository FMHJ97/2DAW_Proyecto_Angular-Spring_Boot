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

  // Método para obtener el listado de relatos de un usuario
  getRelatosByUsuario(id_usuario: string): Observable<any[]> {
    return this.http.post<any[]>(`${this.baseUrl}/relatos/allByUsuario`, { id: id_usuario });
  }

  // Método para obtener un relato por ID
  getRelatoById(id: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/relatos/get`, { id });
  }

  // Método para insertar un nuevo relato
  insertarRelato(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/relatos/new`, datos);
  }

  // Método para actualizar un relato existente
  updateRelato(datos: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/relatos/edit`, datos);
  }


  // Método para eliminar un relato por ID
  deleteRelato(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/relatos/delete`, {
      body: { id },
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }

  // Método para obtener el listado de géneros
  getGeneros(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/generos/all`);
  }

  // Método para insertar géneros a un relato.
  insertarGenerosRelato(data: { id_genero: number, id_relato: number }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/relato-generos/new`, data);  // Ajusta la URL según tu API
  }

  // Método para eliminar los géneros de un relato.
  deleteGenerosRelato(id_relato: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/relato-generos/deleteByRelato`, {
      body: { id: id_relato },
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }

  // Método para obtener el listado de usuarios
  getUsuarios(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/usuarios/all`);
  }

  // Método para eliminar un usuario por ID
  deleteUsuario(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/usuarios/delete`, {
      body: { id },
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }

  // Método para actualizar un usuario
  updateUsuario(datos: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/usuarios/edit`, datos);
  }

  registrarUsuario(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios/new`, datos);
  }

  // Método para iniciar sesión
  login(credentials: any): Observable<any> {
    // Realiza la petición POST al backend
    return this.http.post<any>(`${this.baseUrl}/usuarios/auth`, credentials).pipe(
      // Realiza acciones con la respuesta
      tap(response => {
        // Si la respuesta es correcta, guarda el token en el localStorage
        if (response.result === 'ok') {
          localStorage.setItem('jwt', response.jwt);
          // Obtiene el usuario autenticado y lo guarda en el localStorage
          this.getAuthenticatedUser().subscribe(user => {
            this.saveUserData(response.jwt, user);
          });
        }
      })
    );
  }

  // Método para obtener el usuario autenticado
  getAuthenticatedUser(): Observable<any> {
    // Obtiene el token del localStorage
    const token = localStorage.getItem('jwt');
    if (!token) {
      return throwError(() => new Error('No hay token disponible'));
    }
    // Añade el token a la cabecera de la petición HTTP
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    // Realiza la petición GET al backend para obtener el usuario autenticado
    return this.http.get<any>(`${this.baseUrl}/usuarios/who`, { headers }).pipe(
      tap(user => this.userSubject.next(user)) // Actualiza el usuario en tiempo real
    );
  }

  // Método para guardar el token y el usuario en el localStorage
  saveUserData(token: string, user: any): void {
    localStorage.setItem('jwt', token);
    localStorage.setItem('user', JSON.stringify(user));
    this.userSubject.next(user); // Notifica a los componentes suscritos
  }

  // Método para obtener el usuario del localStorage
  getUserFromLocalStorage(): any {
    if (typeof window !== 'undefined' && localStorage) {
      const user = localStorage.getItem('user');
      return user ? JSON.parse(user) : null;
    }
    return null;
  }

  // Método para cerrar sesión
  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    this.userSubject.next(null); // Notifica a los componentes que ya no hay usuario
  }
}

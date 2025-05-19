import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { LoginRequest, LoginResponse, User } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  private tokenSubject = new BehaviorSubject<string | null>(null);

  public currentUser$ = this.currentUserSubject.asObservable();
  public token$ = this.tokenSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadStoredUserData();
  }

  private loadStoredUserData(): void {
    const storedToken = localStorage.getItem('token');
    const storedUser = localStorage.getItem('currentUser');

    if (storedToken && storedUser) {
      this.tokenSubject.next(storedToken);
      this.currentUserSubject.next(JSON.parse(storedUser));
    }
  }

  login(username: string, password: string): Observable<LoginResponse> {
    const loginRequest: LoginRequest = { username, password };

    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest).pipe(
      tap(response => {
        // Store token and user info
        localStorage.setItem('token', response.token);

        const user: User = {
          username: response.username,
          roles: response.roles
        };

        localStorage.setItem('currentUser', JSON.stringify(user));

        // Update subjects
        this.tokenSubject.next(response.token);
        this.currentUserSubject.next(user);
      }),
      catchError(error => {
        console.error('Login error:', error);
        return throwError(() => new Error('Invalid credentials or server error'));
      })
    );
  }

  logout(): void {
    // Clear stored data
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');

    // Reset subjects
    this.tokenSubject.next(null);
    this.currentUserSubject.next(null);
  }

  get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  get tokenValue(): string | null {
    return this.tokenSubject.value;
  }

  isLoggedIn(): boolean {
    return !!this.tokenValue;
  }

  hasRole(role: string): boolean {
    const user = this.currentUserValue;
    return !!user && user.roles.includes(role);
  }
}

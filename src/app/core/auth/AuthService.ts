import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, tap } from 'rxjs';
import { RegisterRequest } from '../../shared/models/register-request.model';
import { LoginRequest } from '../../shared/models/login-request.model';
import { UserModel } from '../../shared/models/user.model';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
   private apiUrl = 'http://localhost:8080/auth';
  private userKey = 'user';
  private tokenKey = 'authToken';

  constructor(private http: HttpClient) {}

  register(data: RegisterRequest): Observable<UserModel> {
    return this.http.post<UserModel>(`${this.apiUrl}/register`, data);
  }

  login(data: LoginRequest): Observable<UserModel> {
  console.log('Calling login API with:', data);

  return this.http.post<UserModel>(`${this.apiUrl}/login`, data).pipe(
    tap((user) => {
      console.log('Login API response:', user);

      const basicToken = 'Basic ' + btoa(`${data.email}:${data.password}`);
      localStorage.setItem(this.userKey, JSON.stringify(user));
      localStorage.setItem(this.tokenKey, basicToken);
    })
  );
}
  logout(): void {
    localStorage.removeItem(this.userKey);
    localStorage.removeItem(this.tokenKey);
  }

  getUser(): UserModel | null {
    const user = localStorage.getItem(this.userKey);
    return user ? JSON.parse(user) as UserModel : null;
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUserRole(): string | null {
    return this.getUser()?.role ?? null;
  }

  isAdmin(): boolean {
    return this.getUserRole() === 'ADMIN';
  }

  isCustomer(): boolean {
    return this.getUserRole() === 'CUSTOMER';
  }
}

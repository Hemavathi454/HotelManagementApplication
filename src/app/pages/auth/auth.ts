import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../core/auth/AuthService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-auth',
  imports: [CommonModule,FormsModule],
  templateUrl: './auth.html',
  styleUrl: './auth.css',
})
export class Auth implements OnInit {
 

isLoginMode = false;
  isAdminMode = false;
  returnUrl = '';

  registerData = {
    name: '',
    email: '',
    password: ''
  };

  loginData = {
    email: '',
    password: ''
  };

  message = '';
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.isAdminMode = this.route.snapshot.data['adminOnly'] || false;
    this.returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '';

    if (this.isAdminMode) {
      this.isLoginMode = true;
    }
  }

  toggleMode(): void {
    if (this.isAdminMode) return;

    this.isLoginMode = !this.isLoginMode;
    this.message = '';
    this.errorMessage = '';
  }

  register(): void {
    this.message = '';
    this.errorMessage = '';

    this.authService.register(this.registerData).subscribe({
      next: () => {
        this.message = 'Registration successful. Please login.';
        this.isLoginMode = true;

        this.registerData = {
          name: '',
          email: '',
          password: ''
        };
      },
      error: (err) => {
        console.error('Registration error:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Registration failed';
      }
    });
  }

  login(): void {
    this.message = '';
    this.errorMessage = '';

    this.authService.login(this.loginData).subscribe({
      next: (user) => {
        if (this.isAdminMode) {
          if (user.role !== 'ADMIN') {
            this.authService.logout();
            this.errorMessage = 'Access denied: Admins only';
            return;
          }

          if (this.returnUrl) {
            this.router.navigateByUrl(this.returnUrl);
          } else {
            this.router.navigate(['/admin/dashboard']);
          }
          return;
        }

        if (user.role === 'CUSTOMER') {
          if (this.returnUrl) {
            this.router.navigateByUrl(this.returnUrl);
          } else {
            this.router.navigate(['/user/dashboard']);
          }
          return;
        }

        if (user.role === 'ADMIN') {
          this.router.navigate(['/admin/dashboard']);
        }
      },
      error: (err) => {
        console.error('Login error:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Login failed';
      }
    });
  }
}



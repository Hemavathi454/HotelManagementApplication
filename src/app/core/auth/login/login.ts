import { Component } from '@angular/core';
import { LoginRequest } from '../../../shared/models/login-request.model';
import { AuthService } from '../AuthService';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-login',
  imports: [FormsModule],
   standalone: true,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginData: LoginRequest = {
    email: '',
    password: ''
  };

  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

onLogin(form: NgForm): void {
  console.log('Login button clicked');
  console.log(this.loginData);

  this.errorMessage = '';

  if (form.invalid) {
    if (!this.loginData.email?.trim()) {
      this.errorMessage = 'Email is required';
    } else if (!form.controls['email']?.valid) {
      this.errorMessage = 'Enter a valid email address';
    } else if (!this.loginData.password?.trim()) {
      this.errorMessage = 'Password is required';
    } else {
      this.errorMessage = 'Please fill all required fields correctly';
    }
    return;
  }

  this.authService.login(this.loginData).subscribe({
    next: (user) => {
      console.log('Login success:', user);

      if (user.role === 'ADMIN') {
        this.router.navigate(['/admin/dashboard']);
      } else {
        this.router.navigate(['/user/dashboard']);
      }
    },
   error: (err) => {
  console.log('Login error:', err);

  if (err.status === 0) {
    this.errorMessage = 'Cannot connect to server';
    return;
  }

  if (err?.error?.fieldErrors) {
    const fieldErrors = err.error.fieldErrors;
    this.errorMessage =
      fieldErrors.email ||
      fieldErrors.password ||
      'Please fill all required fields correctly';
    return;
  }

  if (typeof err?.error === 'string') {
    this.errorMessage = 'Please fill all required fields correctly';
    return;
  }

  this.errorMessage = err?.error?.message || 'Invalid email or password';
}
  });
}
}

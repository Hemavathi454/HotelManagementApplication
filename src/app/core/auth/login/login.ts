import { Component } from '@angular/core';
import { LoginRequest } from '../../../shared/models/login-request.model';
import { AuthService } from '../AuthService';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

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

  onLogin(): void {
  console.log('Login button clicked');
  console.log(this.loginData);

  this.errorMessage = '';

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
      this.errorMessage = err?.error?.message || err?.error || 'Invalid email or password';
    },
    complete: () => {
      console.log('Login request completed');
    }
  });
}

}

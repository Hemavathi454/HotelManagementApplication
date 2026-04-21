import { Component } from '@angular/core';
import { AuthService } from '../AuthService';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../../shared/models/register-request.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
   standalone: true,
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
    registerData: RegisterRequest = {
    name: '',
    email: '',
    password: ''
  };

  errorMessage = '';
  successMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onRegister(): void {
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.register(this.registerData).subscribe({
      next: () => {
        this.successMessage = 'Registration successful';
        this.router.navigate(['/login']);
      },
      error: () => {
        this.errorMessage = 'Registration failed';
      }
    });
  }

}

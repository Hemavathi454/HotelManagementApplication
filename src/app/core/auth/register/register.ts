import { Component } from '@angular/core';
import { AuthService } from '../AuthService';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../../shared/models/register-request.model';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
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

 onRegister(form: NgForm): void {
  this.errorMessage = '';
  this.successMessage = '';

  if (form.invalid) {
    if (!this.registerData.name?.trim()) {
      this.errorMessage = 'Name is required';
    } else if (!this.registerData.email?.trim()) {
      this.errorMessage = 'Email is required';
    } else if (!form.controls['email']?.valid) {
      this.errorMessage = 'Enter a valid email address';
    } else if (!this.registerData.password?.trim()) {
      this.errorMessage = 'Password is required';
    } else if ((this.registerData.password?.length || 0) < 6) {
      this.errorMessage = 'Password must be at least 6 characters';
    } else {
      this.errorMessage = 'Please fill all required fields correctly';
    }
    return;
  }

  this.authService.register(this.registerData).subscribe({
    next: () => {
      this.successMessage = 'Registration successful';

      this.registerData = {
        name: '',
        email: '',
        password: ''
      };

      form.resetForm();
      this.router.navigate(['/login']);
    },
error: (err) => {
  console.log('Register error:', err);

  if (err.status === 0) {
    this.errorMessage = 'Cannot connect to server';
    return;
  }

  if (err?.error?.fieldErrors) {
    const fieldErrors = err.error.fieldErrors;
    this.errorMessage =
      fieldErrors.name ||
      fieldErrors.email ||
      fieldErrors.password ||
      'Please fill all required fields correctly';
    return;
  }

  if (typeof err?.error === 'string') {
    this.errorMessage = 'Please fill all required fields correctly';
    return;
  }

  this.errorMessage = err?.error?.message || 'Registration failed';
}
  });
}
}

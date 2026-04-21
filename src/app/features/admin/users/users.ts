import { Component } from '@angular/core';
import { UserModel } from '../../../shared/models/user.model';
import { UserService } from '../../../shared/services/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users {
  users: UserModel[] = [];

  userForm: UserModel = {
    name: '',
    email: '',
    role: 'CUSTOMER'
  };

  editingUserId: number | null = null;
  message = '';
  errorMessage = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Error loading users:', err);
        this.errorMessage = 'Failed to load users';
      }
    });
  }

  editUser(user: UserModel): void {
    this.editingUserId = user.id ?? null;
    this.userForm = {
      id: user.id,
      name: user.name,
      email: user.email,
      role: user.role
    };
  }

  updateUser(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingUserId === null) {
      this.errorMessage = 'Please select a user to edit';
      return;
    }

    this.userService.updateUser(this.editingUserId, this.userForm).subscribe({
      next: () => {
        this.message = 'User updated successfully';
        this.resetForm();
        this.loadUsers();
      },
      error: (err) => {
        console.error('Error updating user:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to update user';
      }
    });
  }

  deleteUser(id: number): void {
    this.message = '';
    this.errorMessage = '';

    this.userService.deleteUser(id).subscribe({
      next: () => {
        this.message = 'User deleted successfully';
        this.loadUsers();
      },
      error: (err) => {
        console.error('Error deleting user:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to delete user';
      }
    });
  }

  resetForm(): void {
    this.userForm = {
      name: '',
      email: '',
      role: 'CUSTOMER'
    };
    this.editingUserId = null;
  }

}

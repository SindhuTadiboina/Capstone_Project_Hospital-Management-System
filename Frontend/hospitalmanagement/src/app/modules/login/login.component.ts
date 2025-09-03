import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  selectedRole: string = 'PATIENT'; 
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (!this.email || !this.password) {
      this.errorMessage = 'Please enter email and password';
      return;
    }

    this.authService.login({ email: this.email, password: this.password })
      .subscribe({
        next: (response: any) => { 
          console.log('Login successful:', response);

          // Save JWT token and role
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);

          // Navigate based on role
          if (response.role === 'ADMIN') this.router.navigate(['/admin-dashboard']);
          else if (response.role === 'DOCTOR') this.router.navigate(['/doctor-dashboard']);
          else this.router.navigate(['/patient-dashboard']);
        },
        error: (err: any) => { 
          console.error('Login failed:', err);
          this.errorMessage = err.error?.message || 'Login failed. Please try again.';
        }
      });
  }
}

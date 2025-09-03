import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  selectedRole: string = '';
  name = '';
  email = '';
  password = '';
  confirmPassword = '';

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.selectedRole = params['role'] || 'UNKNOWN';
    });
  }

  onSignup() {
    if (this.password !== this.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    const signupData = {
      name: this.name,
      email: this.email,
      password: this.password
    };

    if (this.selectedRole === 'DOCTOR') {
      this.authService.signupDoctor(signupData).subscribe({
        next: () => {
          alert("Doctor signup successful!");
          this.router.navigate(['/login'], { queryParams: { role: 'DOCTOR' } });
        },
        error: () => alert("Doctor signup failed")
      });
    } else if (this.selectedRole === 'PATIENT') {
      this.authService.signupPatient(signupData).subscribe({
        next: () => {
          alert("Patient signup successful!");
          this.router.navigate(['/login'], { queryParams: { role: 'PATIENT' } });
        },
        error: () => alert("Patient signup failed")
      });
    } else if (this.selectedRole === 'ADMIN') {
      this.authService.signupAdmin(signupData).subscribe({
        next: () => {
          alert("Admin signup successful!");
          this.router.navigate(['/login'], { queryParams: { role: 'ADMIN' } });
        },
        error: () => alert("Admin signup failed")
      });
    }
  }
}

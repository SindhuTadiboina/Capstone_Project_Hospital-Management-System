import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-appointments',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container mt-5 text-center">
      <h2>Doctor Appointments</h2>
      <p>Here you can see all your appointments.</p>
      <button class="btn btn-secondary mt-3" (click)="goBack()">⬅ Back to Dashboard</button>
    </div>
  `
})
export class DoctorAppointmentsComponent {
  constructor(private router: Router) {}
  goBack() {
    this.router.navigate(['/doctor-dashboard']);
  }
}

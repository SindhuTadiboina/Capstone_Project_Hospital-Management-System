import { Routes } from '@angular/router';
import { LoginComponent } from './modules/login/login.component';
import { DoctorDashboardComponent } from './modules/doctor/doctor-dashboard.component';
import { PatientDashboardComponent } from './modules/patient/patient-dashboard.component';
import { AdminDashboardComponent } from './modules/admin/admin-dashboard.component';


export const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },       // generic login
  { path: 'doctor-dashboard', component: DoctorDashboardComponent },
  { path: 'patient-dashboard', component: PatientDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: '**', redirectTo: '/login' }
];

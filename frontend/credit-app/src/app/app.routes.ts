import { Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { CustomerFormComponent } from './components/customer/customer-form/customer-form.component';
import { CreditListComponent } from './components/credit/credit-list/credit-list.component';
import { CreditFormComponent } from './components/credit/credit-form/credit-form.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'customers', component: CustomerListComponent, canActivate: [AuthGuard] },
    { path: 'customers/new', component: CustomerFormComponent, canActivate: [AuthGuard] },
    { path: 'customers/edit/:id', component: CustomerFormComponent, canActivate: [AuthGuard] },
    { path: 'credits', component: CreditListComponent, canActivate: [AuthGuard] },
    { path: 'credits/new', component: CreditFormComponent, canActivate: [AuthGuard] },
    { path: 'credits/edit/:id', component: CreditFormComponent, canActivate: [AuthGuard] },
    { path: '**', component: NotFoundComponent }
];

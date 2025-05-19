import { Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { CustomerFormComponent } from './components/customer/customer-form/customer-form.component';
import { CreditListComponent } from './components/credit/credit-list/credit-list.component';
import { CreditFormComponent } from './components/credit/credit-form/credit-form.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'customers', component: CustomerListComponent },
    { path: 'customers/new', component: CustomerFormComponent },
    { path: 'customers/edit/:id', component: CustomerFormComponent },
    { path: 'credits', component: CreditListComponent },
    { path: 'credits/new', component: CreditFormComponent },
    { path: 'credits/edit/:id', component: CreditFormComponent },
    { path: '**', component: NotFoundComponent }
];

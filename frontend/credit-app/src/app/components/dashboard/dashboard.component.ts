import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgIf, NgFor, NgClass, CurrencyPipe, DatePipe } from '@angular/common';
import { CustomerService } from '../../services/customer.service';
import { CreditService } from '../../services/credit.service';
import { Credit, StatutCredit } from '../../models/credit.model';
import { forkJoin } from 'rxjs';

interface CreditWithCustomer extends Credit {
  customerName: string;
}

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, NgIf, NgFor, NgClass, CurrencyPipe, DatePipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  customerCount = 0;
  creditCount = 0;
  pendingCreditCount = 0;
  recentCredits: CreditWithCustomer[] = [];
  loading = true;

  constructor(
    private customerService: CustomerService,
    private creditService: CreditService
  ) { }

  ngOnInit(): void {
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    this.loading = true;

    // Use forkJoin to make parallel requests
    forkJoin({
      customers: this.customerService.getAllCustomers(),
      credits: this.creditService.getAllCredits(),
      pendingCredits: this.creditService.getCreditsByStatut(StatutCredit.EN_ATTENTE)
    }).subscribe({
      next: (results) => {
        this.customerCount = results.customers.length;
        this.creditCount = results.credits.length;
        this.pendingCreditCount = results.pendingCredits.length;

        // Get the 5 most recent credits
        const sortedCredits = [...results.credits].sort((a, b) =>
          new Date(b.dateDemande).getTime() - new Date(a.dateDemande).getTime()
        ).slice(0, 5);

        // Add customer names to credits
        this.recentCredits = sortedCredits.map(credit => {
          const customer = results.customers.find(c => c.id === credit.customerId);
          return {
            ...credit,
            customerName: customer ? customer.name : 'Unknown'
          };
        });

        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading dashboard data:', error);
        this.loading = false;
      }
    });
  }
}

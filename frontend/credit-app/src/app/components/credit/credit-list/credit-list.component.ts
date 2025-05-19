import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NgIf, NgFor, NgClass, CurrencyPipe, DatePipe } from '@angular/common';
import { CreditService } from '../../../services/credit.service';
import { CustomerService } from '../../../services/customer.service';
import { Credit, StatutCredit } from '../../../models/credit.model';
import { forkJoin } from 'rxjs';

interface CreditWithDetails extends Credit {
  customerName: string;
  type: string;
}

@Component({
  selector: 'app-credit-list',
  imports: [RouterLink, NgIf, NgFor, NgClass, CurrencyPipe, DatePipe],
  templateUrl: './credit-list.component.html',
  styleUrl: './credit-list.component.scss'
})
export class CreditListComponent implements OnInit {
  credits: CreditWithDetails[] = [];
  filteredCredits: CreditWithDetails[] = [];
  loading = true;
  currentFilter: string = 'ALL';
  customerId?: number;

  constructor(
    private creditService: CreditService,
    private customerService: CustomerService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['customerId']) {
        this.customerId = +params['customerId'];
      }

      if (params['status']) {
        this.currentFilter = params['status'];
      }

      this.loadCredits();
    });
  }

  loadCredits(): void {
    this.loading = true;

    const creditObservable = this.customerId
      ? this.creditService.getCreditsByCustomerId(this.customerId)
      : this.creditService.getAllCredits();

    forkJoin({
      credits: creditObservable,
      customers: this.customerService.getAllCustomers()
    }).subscribe({
      next: (results) => {
        this.credits = results.credits.map(credit => {
          const customer = results.customers.find(c => c.id === credit.customerId);

          // Determine credit type based on API endpoint structure
          let type = 'Unknown';
          if ('motif' in credit && 'raisonSociale' in credit) {
            type = 'Professional';
          } else if ('motif' in credit) {
            type = 'Personal';
          } else if ('typeBien' in credit) {
            type = 'Real Estate';
          }

          return {
            ...credit,
            customerName: customer ? customer.name : 'Unknown',
            type
          };
        });

        this.applyFilter();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading credits:', error);
        this.loading = false;
      }
    });
  }

  filterByStatus(status: string): void {
    this.currentFilter = status;
    this.applyFilter();
  }

  private applyFilter(): void {
    if (this.currentFilter === 'ALL') {
      this.filteredCredits = [...this.credits];
    } else {
      this.filteredCredits = this.credits.filter(credit =>
        credit.statut === this.currentFilter as StatutCredit
      );
    }
  }
}

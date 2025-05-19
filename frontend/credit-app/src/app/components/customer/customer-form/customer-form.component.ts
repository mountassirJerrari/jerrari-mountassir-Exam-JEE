import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NgIf, NgClass } from '@angular/common';
import { CustomerService } from '../../../services/customer.service';
import { Customer } from '../../../models/customer.model';

@Component({
  selector: 'app-customer-form',
  imports: [ReactiveFormsModule, RouterLink, NgIf, NgClass],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.scss'
})
export class CustomerFormComponent implements OnInit {
  customerForm!: FormGroup;
  isEditMode = false;
  customerId?: number;
  loading = false;
  submitted = false;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });

    // Check if we're in edit mode
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.customerId = +params['id'];
        this.loadCustomer(this.customerId);
      }
    });
  }

  // Convenience getter for easy access to form fields
  get f() { return this.customerForm.controls; }

  loadCustomer(id: number): void {
    this.loading = true;
    this.customerService.getCustomerById(id).subscribe({
      next: (customer) => {
        this.customerForm.patchValue({
          name: customer.name,
          email: customer.email
        });
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading customer:', error);
        this.error = 'Failed to load customer details.';
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    // Stop here if form is invalid
    if (this.customerForm.invalid) {
      return;
    }

    this.loading = true;
    const customer: Customer = {
      name: this.f['name'].value,
      email: this.f['email'].value
    };

    if (this.isEditMode) {
      this.customerService.updateCustomer(this.customerId!, customer).subscribe({
        next: () => {
          this.router.navigate(['/customers']);
        },
        error: (error) => {
          console.error('Error updating customer:', error);
          this.error = 'Failed to update customer. Please try again.';
          this.loading = false;
        }
      });
    } else {
      this.customerService.createCustomer(customer).subscribe({
        next: () => {
          this.router.navigate(['/customers']);
        },
        error: (error) => {
          console.error('Error creating customer:', error);
          this.error = 'Failed to create customer. Please try again.';
          this.loading = false;
        }
      });
    }
  }
}

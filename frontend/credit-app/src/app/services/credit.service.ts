import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credit, CreditPersonnel, CreditImmobilier, CreditProfessionnel, StatutCredit } from '../models/credit.model';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8080/api/credits';

  constructor(private http: HttpClient) { }

  // General credit methods
  getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(this.apiUrl);
  }

  getCreditById(id: number): Observable<Credit> {
    return this.http.get<Credit>(`${this.apiUrl}/${id}`);
  }

  getCreditsByCustomerId(customerId: number): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.apiUrl}/customer/${customerId}`);
  }

  getCreditsByStatut(statut: StatutCredit): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.apiUrl}/statut/${statut}`);
  }

  // Credit Personnel methods
  createCreditPersonnel(credit: CreditPersonnel): Observable<CreditPersonnel> {
    return this.http.post<CreditPersonnel>(`${this.apiUrl}/personnel`, credit);
  }

  updateCreditPersonnel(id: number, credit: CreditPersonnel): Observable<CreditPersonnel> {
    return this.http.put<CreditPersonnel>(`${this.apiUrl}/personnel/${id}`, credit);
  }

  getCreditPersonnelByMotif(motif: string): Observable<CreditPersonnel[]> {
    return this.http.get<CreditPersonnel[]>(`${this.apiUrl}/personnel/motif/${motif}`);
  }

  // Credit Immobilier methods
  createCreditImmobilier(credit: CreditImmobilier): Observable<CreditImmobilier> {
    return this.http.post<CreditImmobilier>(`${this.apiUrl}/immobilier`, credit);
  }

  updateCreditImmobilier(id: number, credit: CreditImmobilier): Observable<CreditImmobilier> {
    return this.http.put<CreditImmobilier>(`${this.apiUrl}/immobilier/${id}`, credit);
  }

  // Credit Professionnel methods
  createCreditProfessionnel(credit: CreditProfessionnel): Observable<CreditProfessionnel> {
    return this.http.post<CreditProfessionnel>(`${this.apiUrl}/professionnel`, credit);
  }

  updateCreditProfessionnel(id: number, credit: CreditProfessionnel): Observable<CreditProfessionnel> {
    return this.http.put<CreditProfessionnel>(`${this.apiUrl}/professionnel/${id}`, credit);
  }

  getCreditProfessionnelByRaisonSociale(raisonSociale: string): Observable<CreditProfessionnel[]> {
    return this.http.get<CreditProfessionnel[]>(`${this.apiUrl}/professionnel/raison-sociale/${raisonSociale}`);
  }
}

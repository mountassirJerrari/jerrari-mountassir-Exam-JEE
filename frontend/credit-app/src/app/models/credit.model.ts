export enum StatutCredit {
  EN_ATTENTE = 'EN_ATTENTE',
  ACCEPTE = 'ACCEPTE',
  REFUSE = 'REFUSE'
}

export interface Credit {
  id?: number;
  dateDemande: Date;
  statut: StatutCredit;
  dateAcceptation?: Date;
  montant: number;
  dureeRemboursement: number;
  tauxInteret: number;
  customerId: number;
  remboursementIds?: number[];
}

export interface CreditPersonnel extends Credit {
  motif: string;
}

export interface CreditImmobilier extends Credit {
  typeBien: string;
}

export interface CreditProfessionnel extends Credit {
  motif: string;
  raisonSociale: string;
}

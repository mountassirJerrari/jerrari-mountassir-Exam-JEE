export enum TypeRemboursement {
  MENSUEL = 'MENSUEL',
  TRIMESTRIEL = 'TRIMESTRIEL',
  SEMESTRIEL = 'SEMESTRIEL',
  ANNUEL = 'ANNUEL'
}

export interface Remboursement {
  id?: number;
  date: Date;
  montant: number;
  type: TypeRemboursement;
  creditId: number;
}

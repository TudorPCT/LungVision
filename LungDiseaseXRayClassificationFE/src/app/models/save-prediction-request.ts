export interface SavePredictionRequest{
  patientFirstName: string;
  patientLastName: string;
  patientAge: number;
  patientGender: string;
  scanDate: string;
  file: string;
  fileName: string;
  fileType: string;
}

import {Component, OnInit} from '@angular/core';
import {SavedPredictionDto} from "../../../models/saved-prediction-dto";
import {TranslateService} from "@ngx-translate/core";
import {DatePipe} from "@angular/common";
import {PredictionService} from "../../../services/prediction/prediction.service";
import {MessageService} from "primeng/api";
import {catchError, tap} from "rxjs/operators";
import {PredictionDto} from "../../../models/prediction-dto";
import {EMPTY} from "rxjs";

@Component({
  selector: 'app-predictions-table',
  templateUrl: './predictions-table.component.html',
  styleUrls: ['./predictions-table.component.css']
})
export class PredictionsTableComponent implements OnInit{
  predictions: SavedPredictionDto[] = [];
  clonedPredictions: { [s: string]: SavedPredictionDto; } = {};
  selectedPrediction: SavedPredictionDto | undefined;
  chosenDate: Date = new Date();
  loading: boolean = true;
  scan?: PredictionDto;

  constructor(public translate: TranslateService,
              public datePipe: DatePipe,
              public predictionService: PredictionService,
              public messageService: MessageService) {}
  ngOnInit(): void {
    this.predictionService.getAllPredictions().subscribe((predictions: SavedPredictionDto[]) => {
      this.predictions = predictions;
      this.loading = false;
    });
  }

  getSeverity(prediction: string) {
    if (prediction == 'NORMAL') return 'success';
    else return 'error';
  }

  editPrediction(prediction: SavedPredictionDto) {
    this.clonedPredictions[prediction.id] = {...prediction};
  }

  deletePrediction(prediction: any, index: number) {
    this.predictionService.delete(prediction.id).pipe(
      tap((response: string) => {
        delete this.predictions[index];
      }),
      catchError((err: any) => {
        return EMPTY;
      })
    ).subscribe();
  }

  onEditSave(prediction: SavedPredictionDto, index: number) {
    prediction.scanDate = this.datePipe.transform(this.chosenDate, 'yyyy-MM-ddTHH:mm:ss.SSS')!;
    this.predictionService.update(prediction).pipe(
      tap((response: string) => {
        delete this.clonedPredictions[prediction.id];
      }),
      catchError((err: any) => {
        this.onEditCancel(prediction, index);
        return EMPTY;
      })
    ).subscribe();
  }

  onEditCancel(prediction: SavedPredictionDto, index: number) {
    this.predictions[index] = this.clonedPredictions[prediction.id];
    delete this.clonedPredictions[prediction.id];
  }
}

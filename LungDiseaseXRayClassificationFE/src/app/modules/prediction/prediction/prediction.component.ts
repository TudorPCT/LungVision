import { Component } from '@angular/core';
import {PredictionDto} from "../../../models/prediction-dto";

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.css']
})
export class PredictionComponent {
  scan: PredictionDto | undefined;
  showModal: boolean = false;
  constructor() { }
}

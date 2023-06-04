import {Component, Input} from '@angular/core';
import {PredictionDto} from "../../../models/prediction-dto";

@Component({
  selector: 'app-scan-display',
  templateUrl: './scan-display.component.html',
  styleUrls: ['./scan-display.component.css']
})
export class ScanDisplayComponent{
  @Input() scan: PredictionDto | undefined;
  imagePath: string;

  constructor() {
    this.imagePath = 'https://res.cloudinary.com/dz8ngjppi/image/upload/v1685911852/predict_image_wz2flm.png';
  }
}

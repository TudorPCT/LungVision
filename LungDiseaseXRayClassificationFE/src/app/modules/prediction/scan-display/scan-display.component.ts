import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-scan-display',
  templateUrl: './scan-display.component.html',
  styleUrls: ['./scan-display.component.css']
})
export class ScanDisplayComponent{
  @Input() scan: string | undefined;
  imagePath: string;

  constructor() {
    this.imagePath = '/assets/images/predict_image.png';
  }
}

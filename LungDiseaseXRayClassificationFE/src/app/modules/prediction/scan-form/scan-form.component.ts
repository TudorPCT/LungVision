import {Component, ElementRef, EventEmitter, OnInit, Output, Renderer2} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {PredictionDto} from "../../../models/prediction-dto";
import {PredictionService} from "../../../services/prediction/prediction.service";
import {PredictionResponse} from "../../../models/prediction-response";
import {lastValueFrom} from "rxjs";
@Component({
  selector: 'app-scan-form',
  templateUrl: './scan-form.component.html',
  styleUrls: ['./scan-form.component.css']
})
export class ScanFormComponent implements OnInit{
  @Output() scan = new EventEmitter<PredictionDto>();
  @Output() onSavePressed = new EventEmitter<boolean>();
  phase: string;
  uploadedFile: any = null;
  chooseLabel?: string;
  uploadLabel?: string;
  cancelLabel?: string;
  info?: string;
  reader : FileReader;

  data : any;
  options = {
    responsive: true,
    maintainAspectRatio: false,
  };
  resultMessage: String = '';

  predictionRequest?: PredictionDto;

  constructor(public predictionService: PredictionService,
              public translate: TranslateService,
              private renderer: Renderer2,
              private el: ElementRef) {
    this.phase = 'choose';

    this.reader = new FileReader();
    this.reader.onload = () => {
      const scanSrc = this.reader.result as string;

      this.predictionRequest = {
        file: scanSrc?.split(',')[1],
        fileName: this.uploadedFile.name.split('.')[0],
        fileType: this.uploadedFile.type.split('/')[1].toUpperCase()
      }

      this.scan.emit(this.predictionRequest);
    };
  }

  ngOnInit() {
    this.scan.emit(undefined);

    this.chooseLabel = this.translate.instant('prediction.scan.choose');
    this.uploadLabel = this.translate.instant('prediction.scan.upload');
    this.cancelLabel = this.translate.instant('prediction.scan.cancel');
    this.info = this.translate.instant('prediction.scan.info');
  }

  async onPredict() {
    this.phase = 'loading'
    this.disableElement('#file-upload');

    if(this.predictionRequest == undefined)
      return;

    let prediction = this.predictionService.predict(this.predictionRequest!);
    await lastValueFrom(prediction).then(
      (prediction: PredictionResponse) => {
          this.data = {
            labels: ['Covid19', 'Normal', 'Pneumonia', 'Tuberculosis'],
            datasets: [{
              data: prediction.probabilities,
              backgroundColor: ['red', 'green', 'blue', 'yellow'],
              borderColor: ['#161621', '#161621', '#161621', '#161621']
            }]
          };

          this.resultMessage = this.translate.instant(
            'prediction.scan.result',
            {result: prediction.prediction.toLowerCase()});

          this.phase = 'result';
      },
      (error: any) => {
        this.phase = 'choose'
      });

    this.enableElement('#file-upload');
  }

  onSelect($event : any) {
    this.uploadedFile = $event.files[0]
    this.reader.readAsDataURL($event.files[0] as File);
  }

  onCancel() {
    this.scan.emit(undefined);
    this.predictionRequest = undefined;
    this.phase = 'choose';
  }

  disableElement(elementId: string) {
    const div = this.el.nativeElement.querySelector(elementId);
    this.renderer.setStyle(div, 'pointer-events', 'none');
    this.renderer.setStyle(div, 'opacity', '0.4');
  }

  enableElement(elementId: string) {
    const div = this.el.nativeElement.querySelector(elementId);
    this.renderer.setStyle(div, 'pointer-events', 'all');
    this.renderer.setStyle(div, 'opacity', '1');
  }

}

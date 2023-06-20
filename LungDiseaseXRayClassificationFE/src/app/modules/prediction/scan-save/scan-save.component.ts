import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MessageService} from "primeng/api";
import {PredictionService} from "../../../services/prediction/prediction.service";
import {SavePredictionRequest} from "../../../models/save-prediction-request";
import {PredictionDto} from "../../../models/prediction-dto";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-scan-save',
  templateUrl: './scan-save.component.html',
  styleUrls: ['./scan-save.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ScanSaveComponent implements OnInit{

  @Input() public display: boolean = false;
  @Input() scan: PredictionDto | undefined;
  @Output() displayValueUpdated: EventEmitter<boolean> = new EventEmitter<boolean>();

  title: any;

  scanSaveForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    age: new FormControl('', [Validators.required,
      Validators.min(0), Validators.max(120)]),
  });
  isFirstNameInvalid: boolean = false;
  isLastNameInvalid: boolean = false;
  isAgeInvalid: boolean = false;
  today: Date = new Date();

  selectedDate: Date = new Date();
  genders: any[] = [];
  selectedGender: string = 'Female';
  submitted: boolean = false;

  constructor(private predictionService: PredictionService, private messageService: MessageService, private translate: TranslateService) {}

  ngOnInit(): void {
    this.title = this.translate.instant('prediction.scan.save');

    this.genders = [
      this.translate.instant('gender.female'),
      this.translate.instant('gender.male')
    ]
  }

  onSubmit() {
    if (this.submitted || this.scanSaveForm.invalid || this.scan == undefined)
      return;

    this.submitted = true;

    let savePredictionRequest: SavePredictionRequest = {
      patientFirstName: this.scanSaveForm.value.firstName!,
      patientLastName: this.scanSaveForm.value.lastName!,
      patientAge: Number(this.scanSaveForm.value.age),
      patientGender: this.selectedGender,
      scanDate: this.selectedDate.toISOString(),
      file: this.scan.file,
      fileName: this.scan.fileName,
      fileType: this.scan.fileType
    }

    this.predictionService.save(savePredictionRequest).subscribe();

    this.display = false;
    this.displayValueUpdated.emit(this.display);

    this.submitted = false;
  }

  getFirstNameError() {
    let message: string = this.translate.instant("patient") + " " + this.translate.instant("patient.name.first");
    return this.scanSaveForm.controls.firstName.hasError('required')
      ? this.translate.instant("error.isRequired", {field: message}) : "";
  }

  getLastNameError() {
    let message: string = this.translate.instant("patient") + " " + this.translate.instant("patient.name.last");
    return this.scanSaveForm.controls.lastName.hasError('required')
        ? this.translate.instant("error.isRequired", {field: message}) : "";
  }

  onHide() {
    this.display = false;
    this.displayValueUpdated.emit(this.display);
  }

  onFirstNameFocusOut() {
    this.isFirstNameInvalid = this.scanSaveForm.controls.firstName.hasError('required');
  }

  onLastNameFocusOut() {
    this.isLastNameInvalid = this.scanSaveForm.controls.lastName.hasError('required');
  }

  select($event: any) {
    this.selectedDate = $event ? $event : new Date();
  }

  onAgeFocusOut() {
    this.isAgeInvalid = this.scanSaveForm.controls.age.hasError('required') ||
      this.scanSaveForm.controls.age.hasError('min') ||
      this.scanSaveForm.controls.age.hasError('max');
  }

  getAgeError() {
    let message = this.translate.instant("patient") + " " + this.translate.instant("patient.age");
    return this.scanSaveForm.controls.age.hasError('required') ?
      this.translate.instant("error.isRequired", {field: message}) :
      this.scanSaveForm.controls.age.hasError('min') ||
      this.scanSaveForm.controls.age.hasError('max') ?
        this.translate.instant("error.invalid", {field: message}): '';
  }
}

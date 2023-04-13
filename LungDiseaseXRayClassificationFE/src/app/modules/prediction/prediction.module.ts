import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PredictionRoutingModule } from './prediction-routing.module';
import {PredictionComponent} from "./prediction/prediction.component";
import {SharedModule} from "../shared/shared.module";
import {FileUploadModule} from "primeng/fileupload";
import {PrimeIcons} from "primeng/api";
import {ScanDisplayComponent} from "./scan-display/scan-display.component";
import {ScanFormComponent} from "./scan-form/scan-form.component";
import {ChartModule} from "primeng/chart";


@NgModule({
  declarations: [
    PredictionComponent,
    ScanDisplayComponent,
    ScanFormComponent
  ],
    imports: [
        CommonModule,
        PredictionRoutingModule,
        SharedModule,
        FileUploadModule,
        ChartModule
    ],
  providers: [
    PrimeIcons
  ]
})
export class PredictionModule { }

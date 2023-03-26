import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PredictionRoutingModule } from './prediction-routing.module';
import {PredictionComponent} from "./prediction/prediction.component";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    PredictionComponent
  ],
  imports: [
    CommonModule,
    PredictionRoutingModule,
    SharedModule
  ]
})
export class PredictionModule { }

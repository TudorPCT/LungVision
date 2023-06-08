import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';

import { SavedPredictionsRoutingModule } from './saved-predictions-routing.module';
import { SavedPredictionsComponent } from './saved-predictions/saved-predictions.component';
import { PredictionsTableComponent } from './predictions-table/predictions-table.component';
import {SharedModule} from "../shared/shared.module";
import {TableModule} from "primeng/table";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {TagModule} from "primeng/tag";
import {RippleModule} from "primeng/ripple";
import { FormsModule } from '@angular/forms';
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {DialogModule} from "primeng/dialog";
import {PredictionModule} from "../prediction/prediction.module";
import {ImageModule} from "primeng/image";

@NgModule({
  declarations: [
    SavedPredictionsComponent,
    PredictionsTableComponent
  ],
    imports: [
        CommonModule,
        SavedPredictionsRoutingModule,
        SharedModule,
        TableModule,
        ButtonModule,
        InputTextModule,
        TagModule,
        RippleModule,
        FormsModule,
        DropdownModule,
        CalendarModule,
        DialogModule,
        PredictionModule,
        ImageModule
    ],
  providers: [
    DatePipe
  ]
})
export class SavedPredictionsModule {}

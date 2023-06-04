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
import {AuthInterceptor} from "../../core/interceptors/auth.interceptor";
import {ImageModule} from "primeng/image";
import {ScanSaveComponent} from "./scan-save/scan-save.component";
import {DialogModule} from "primeng/dialog";
import {ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";


@NgModule({
    declarations: [
        PredictionComponent,
        ScanDisplayComponent,
        ScanFormComponent,
        ScanSaveComponent
    ],
    imports: [
        CommonModule,
        PredictionRoutingModule,
        SharedModule,
        FileUploadModule,
        ChartModule,
        ImageModule,
        DialogModule,
        ReactiveFormsModule,
        InputTextModule,
        CalendarModule,
        DropdownModule
    ],
    exports: [
        ScanDisplayComponent
    ],
    providers: [
        PrimeIcons,
    ]
})
export class PredictionModule { }

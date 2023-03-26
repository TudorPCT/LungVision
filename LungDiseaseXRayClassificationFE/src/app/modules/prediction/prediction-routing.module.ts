import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PredictionComponent} from "./prediction/prediction.component";

const routes: Routes = [
  {
    path: '',
    component: PredictionComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PredictionRoutingModule { }

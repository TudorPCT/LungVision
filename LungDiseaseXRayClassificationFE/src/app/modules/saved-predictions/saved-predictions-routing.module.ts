import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SavedPredictionsComponent} from "./saved-predictions/saved-predictions.component";

const routes: Routes = [
  {
    path: '',
    component: SavedPredictionsComponent
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SavedPredictionsRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authGuard} from "./core/guards/auth.guard";
import {NotFoundComponent} from "./modules/shared/not-found/not-found.component";

const routes: Routes = [
  {
      path: '',
      loadChildren: () =>
        import('./modules/home/homepage.module').then((m) => m.HomepageModule)
  },
  {
    canActivate: [authGuard],
    path: 'prediction',
    loadChildren: () =>
      import('./modules/prediction/prediction.module').then((m) => m.PredictionModule)
  },
  {
    canActivate: [authGuard],
    path: 'saved-predictions',
    loadChildren: () =>
      import('./modules/saved-predictions/saved-predictions.module').then((m) => m.SavedPredictionsModule)
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

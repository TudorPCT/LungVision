import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
      path: 'home',
      loadChildren: () =>
        import('./modules/home/homepage.module').then((m) => m.HomepageModule)
  },
  {
      path: '',
      loadChildren: () =>
        import('./modules/prediction/prediction.module').then((m) => m.PredictionModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

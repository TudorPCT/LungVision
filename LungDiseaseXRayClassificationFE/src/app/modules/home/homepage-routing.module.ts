import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HeroComponent} from "./hero/hero.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomepageRoutingModule { }

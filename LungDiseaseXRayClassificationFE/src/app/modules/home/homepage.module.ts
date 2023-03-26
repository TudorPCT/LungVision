import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import {HeroComponent} from "./hero/hero.component";
import {SharedModule} from "../shared/shared.module";
import {HomeComponent} from "./home/home.component";


@NgModule({
  declarations: [
    HomeComponent,
    HeroComponent
  ],

  imports: [
    CommonModule,
    HomepageRoutingModule,
    SharedModule
  ]
})
export class HomepageModule { }

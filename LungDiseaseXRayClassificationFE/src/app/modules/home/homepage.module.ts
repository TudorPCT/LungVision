import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import {HeroComponent} from "./hero/hero.component";
import {SharedModule} from "../shared/shared.module";
import {HomeComponent} from "./home/home.component";
import { AuthComponent } from './auth/auth.component';
import {DialogModule} from "primeng/dialog";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {PasswordModule} from "primeng/password";
import {CheckboxModule} from "primeng/checkbox";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {DividerModule} from "primeng/divider";
import {ToggleButtonModule} from "primeng/togglebutton";

@NgModule({
  declarations: [
    HomeComponent,
    HeroComponent,
    AuthComponent,
  ],

  imports: [
    CommonModule,
    HomepageRoutingModule,
    SharedModule,
    DialogModule,
    FormsModule,
    ReactiveFormsModule,
    PasswordModule,
    CheckboxModule,
    InputTextModule,
    ButtonModule,
    DividerModule,
    ToggleButtonModule,
  ]
})
export class HomepageModule { }

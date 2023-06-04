import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "./header/header.component";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import { NotFoundComponent } from './not-found/not-found.component';
import {ToastModule} from "primeng/toast";
import {MenuModule} from "primeng/menu";



@NgModule({
  declarations: [
    HeaderComponent,
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    DropdownModule,
    ToastModule,
    MenuModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class SharedModule { }

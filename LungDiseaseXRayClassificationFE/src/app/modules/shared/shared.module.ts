import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "./header/header.component";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";



@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    DropdownModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class SharedModule { }

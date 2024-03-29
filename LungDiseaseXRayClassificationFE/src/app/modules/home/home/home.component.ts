import { Component } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  showModal: boolean = false;

  constructor(public authService: AuthService) {}

}

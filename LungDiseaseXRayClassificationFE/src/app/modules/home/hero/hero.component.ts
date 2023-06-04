import { Component } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent {
  headline: string;
  heroImage: string;
  introduction: string;
  constructor(public translateService: TranslateService) {
    this.headline = this.translateService.instant('headline');
    this.introduction = this.translateService.instant('introduction');
    this.heroImage = 'https://res.cloudinary.com/dz8ngjppi/image/upload/v1685911852/lungs_epigrm.png';
  }
}

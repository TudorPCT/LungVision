import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { enableProdMode } from '@angular/core';
import {environment} from "./environments/environment";

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

console.log('Environment', environment);

if (environment.production) {
  enableProdMode();
    window.console.error = function () {
  };
    window.console.warn = function () {
  };
}

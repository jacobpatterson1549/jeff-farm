import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <app-error-messages></app-error-messages>
    <router-outlet></router-outlet>`
})
export class AppComponent { }

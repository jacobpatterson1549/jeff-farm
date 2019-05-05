import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <h1>Just Effective Farm Facilitation</h1>
    <app-error-messages></app-error-messages>
    <router-outlet></router-outlet>`
})
export class AppComponent { }

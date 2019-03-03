import { Component, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-navigation',
  template: '<button [disabled]="disabled" (click)="goBack()">Back</button>',
})
export class NavigationComponent {

  disabled: boolean = ['/', '/farms'].indexOf(this.router.url) >= 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  goBack() {
    this.router.navigate([".."], { relativeTo: this.route });
  }
}

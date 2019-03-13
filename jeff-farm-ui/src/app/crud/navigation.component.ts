import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  template: '<button [disabled]="!canGoUp()" (click)="goUp()">Back</button>',
})
export class NavigationComponent {

  constructor(
    private router: Router,
    private route: ActivatedRoute) { }

  canGoUp() {
    return ['/', '/farms'].indexOf(this.router.url) < 0;
  }

  goUp() {
    this.router.navigate(['../'], { relativeTo: this.route } );
  }
}

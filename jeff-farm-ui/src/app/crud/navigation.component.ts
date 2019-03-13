import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router, UrlTree } from '@angular/router';
import { Location } from '@angular/common';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-navigation',
  template: '<button [disabled]="!canGoUp()" (click)="goUp()">Back</button>',
})
export class NavigationComponent {

  @Input()
  private stepsToParent?: number = 1;

  constructor(
    private router: Router,
    private route: ActivatedRoute) {
      if (this.stepsToParent < 1 || !Number.isInteger(this.stepsToParent)) {
        throw new Error(`Invalid stepsToParent: ${this.stepsToParent}`)
      }
    }

  canGoUp() {
    return ['/', '/farms'].indexOf(this.router.url) < 0;
  }

  goUp() {
    // if the route has the same path as the parents, we must jump up higher.
    let parentRoute: ActivatedRoute = this.route;
    for (var i = 1; i < this.stepsToParent; i++) {
      parentRoute = parentRoute.parent;
    }

    this.router.navigate(['..'], { relativeTo:parentRoute } );
  }
}

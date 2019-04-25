import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  template: '<button type="button" class="btn btn-secondary" [disabled]="!canGoUp()" (click)="goUp()">Back</button>',
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
    // Navigate relative to the component which houses this component (the parent).
    let parentRoute: ActivatedRoute = this.route;
    // Do not jump only to the immediate grandparent only to be redirected back to current parent.
    for (var i = 1; i < this.stepsToParent; i++) {
      parentRoute = parentRoute.parent;
    }

    this.router.navigate(['..'], { relativeTo:parentRoute } );
  }
}

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { AuthService } from '../auth/auth.service';
import { HeaderItem } from './header-item';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private headerService: HeaderService,
    private authService: AuthService) { }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  getHeaderItems(): HeaderItem[] {
    return this.headerService.headerItems;
  }

  // TODO: copied from navigation component.  COMBINE
  goUp(index: number) {
    const stepsToParent: number = this.getHeaderItems().length - index;
    // Navigate relative to the component which houses this component (the parent).
    let parentRoute: ActivatedRoute = this.route;
    // Do not jump only to the immediate grandparent only to be redirected back to current parent.
    for (let i = 1; i < stepsToParent; i++) {
      parentRoute = parentRoute.parent;
    }

    this.router.navigate(['..'], { relativeTo: parentRoute });
  }
}

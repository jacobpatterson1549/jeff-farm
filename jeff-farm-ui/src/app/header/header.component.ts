import { Component } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { HeaderItem } from './header-item';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(
    private headerService: HeaderService,
    private authService: AuthService) { }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  getHeaderItems(): HeaderItem[] {
    return this.headerService.headerItems;
  }
}

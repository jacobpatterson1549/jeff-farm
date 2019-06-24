import { Component } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(
    private headerService: HeaderService,
    private authService: AuthService) { }
}

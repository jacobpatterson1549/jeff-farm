import { Component } from '@angular/core';

import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(private headerService: HeaderService) { }

  getHeaderItems() {
    return this.headerService.headerItems;
  }
}

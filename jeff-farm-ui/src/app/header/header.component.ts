import { Component, OnInit } from '@angular/core';

import { HeaderItem } from './header-item';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  headerItems: HeaderItem[];

  constructor(private headerService: HeaderService) { }

  ngOnInit(): void {
    this.headerItems = this.headerService.headerItems;
  }
}

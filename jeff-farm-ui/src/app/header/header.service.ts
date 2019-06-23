import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { HeaderItem } from './header-item';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  // headerItems: HeaderItem[] = [];
  headerItems: HeaderItem[] = [
    { name: 'a', url: 'aa' },
    { name: 'b', url: 'bb' },
    { name: 'b', url: 'cc' },
  ];

  constructor(private titleService: Title) { }

  setPage(title: string, headerItems: HeaderItem[]) {
    this.titleService.setTitle(title);
    this.headerItems = headerItems;
  }
}

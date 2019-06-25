import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { HeaderItem, HeaderItemType } from './header-item';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  headerItems: HeaderItem[] = [];
  cache: object = {}; // HeaderItemType:id:name

  constructor(private titleService: Title) {
    for (const type in HeaderItemType) {
      if (type !== null) {
        this.cache[type] = {};
      }
    }

    // DEBUG setup:
    this.setPage('xyz', [
      HeaderItem.from('aa', 'a'),
      HeaderItem.from('bb', 'b'),
      HeaderItem.from('cc', 'c'),
      new HeaderItem('/farm/14', HeaderItemType.FARM, 14),
    ]);
  }

  setPage(title: string, headerItems: HeaderItem[]) {
    this.titleService.setTitle(title);
    this.headerItems = headerItems;

    for (const headerItem of this.headerItems) {
      if (headerItem.type !== null && headerItem.id !== null) {
        this.cache[headerItem.type][headerItem.id] = headerItem.name;
      }
      if (!headerItem.name) {
        headerItem.name = this.cache[headerItem.type][headerItem.id]
          || `[${headerItem.getTypeName()} #${headerItem.id}]`;
      }
    }
  }
}

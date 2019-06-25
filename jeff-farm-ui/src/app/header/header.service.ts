import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { getHeaderItemTypeName, HeaderItem, HeaderItemType } from './header-item';

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
  }

  setHeaderItems(headerItems: HeaderItem[]) {
    this.headerItems = headerItems;

    for (const headerItem of this.headerItems) {
      if (headerItem.type !== null && headerItem.id !== null) {
        this.cache[headerItem.type][headerItem.id] = headerItem.name;
      }
      if (!headerItem.name) {
        headerItem.name = this.cache[headerItem.type][headerItem.id]
          || `[${getHeaderItemTypeName(headerItem.type)} #${headerItem.id}]`;
      }
    }
    console.log('setting header items:' + Date.now());
  }
}

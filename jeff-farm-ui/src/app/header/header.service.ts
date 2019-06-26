import { Injectable } from '@angular/core';

import { HeaderItem } from './header-item';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  headerItems: HeaderItem[] = [];

  setHeaderItems(headerItems: HeaderItem[]) {
    this.headerItems = headerItems;
  }
}

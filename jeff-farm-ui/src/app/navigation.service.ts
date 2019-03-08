import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  constructor(
    private router: Router,
    private route: ActivatedRoute) { }

  goBack() {
    this.router.navigate([".."], { relativeTo: this.route });
  }

  getUrl(): string {
    return this.router.url;
  }
}
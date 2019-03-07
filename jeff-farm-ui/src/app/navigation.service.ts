import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, UrlSegment } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  goBack() {
    this.router.navigate([".."], { relativeTo: this.activatedRoute });
  }

  getUrl(): string {
    return this.router.url;
  }

  getRouteParam(name: string) : number {
    const id: string = this.activatedRoute.snapshot.paramMap.get(name);
    return parseInt(id);
  }
}
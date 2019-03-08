import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, UrlSegment } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

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

  getRouteParam(paramName: string) : number {
    // const parentParam: string = this.activatedRoute.parent.snapshot.paramMap.get(paramName);
    // this.activatedRoute.paramMap.pipe(
    //   tap(params =>  {
    //     console.log(params);
    //     console.log('done2');
    //   })
    // );
    this.activatedRoute.params.subscribe(params => {
      // console.log(params.get(paramName));
      console.log(params[paramName]);
      console.log('done');
    });
    const param: string = this.activatedRoute.snapshot.paramMap.get(paramName);
    // const param: string = this.activatedRoute.snapshot.params[paramName];
    // let id2 = +this.activatedRoute.snapshot.params[paramName];
    // let params2 = +this.activatedRoute.snapshot.params;

    if (param == null) {
      throw new Error(`Could not get route param: ${paramName}`)
    }
    return parseInt(param);
  }
}
import { Component, Injectable } from '@angular/core';
import { NavigationService } from '../navigation.service';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-navigation',
  template: '<button [disabled]="disabled" (click)="navigationService.goBack()">Back</button>',
})
export class NavigationComponent {

  disabled: boolean = ['/', '/farms'].indexOf(this.navigationService.getUrl()) >= 0;

  constructor(private navigationService: NavigationService) { }
}

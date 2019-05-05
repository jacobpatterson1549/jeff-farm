import { Component, Input } from '@angular/core';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'spinner',
  template: '<fa-icon [icon]="faSpinner" [hidden]="!visible" [spin]="true"></fa-icon>',
})
export class SpinnerComponent {
  @Input()
  visible: boolean;
  faSpinner = faSpinner;
}

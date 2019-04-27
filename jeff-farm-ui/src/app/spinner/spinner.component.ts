import { Component, Input } from '@angular/core';

@Component({
  selector: 'spinner',
  template: '<i [hidden]="!visible" class="fa fa-spinner fa-spin" style="font-size:24px"></i>',
})
export class SpinnerComponent {
  @Input()
  visible: boolean;
}

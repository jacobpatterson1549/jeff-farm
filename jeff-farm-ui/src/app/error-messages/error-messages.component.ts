import { Component } from '@angular/core';
import { ErrorMessagesService } from './error-messages.service';

@Component({
  selector: 'app-error-messages',
  templateUrl: './error-messages.component.html',
})
export class ErrorMessagesComponent {

  constructor(public errorMessagesService: ErrorMessagesService) { }

  close(index: number) {
    this.errorMessagesService.messages.splice(index, 1);
  }
}

import { Component } from '@angular/core';
import { ErrorMessagesService } from './error-messages.service';
import { ErrorMessage } from './error-message';

@Component({
  selector: 'app-error-messages',
  templateUrl: './error-messages.component.html',
})
export class ErrorMessagesComponent {

  constructor(public errorMessagesService: ErrorMessagesService) { }

  getErrorMessages(): ErrorMessage[] {
    return this.errorMessagesService.errorMessages;
  }
}

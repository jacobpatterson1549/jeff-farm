import { Component } from '@angular/core';
import { ErrorMessagesService } from './error-messages.service';

@Component({
  selector: 'error-messages',
  templateUrl: './error-messages.component.html',
})
export class ErrorMessagesComponent {

  constructor(public errorMessagesService: ErrorMessagesService) { }

  close(message: string) {
    // TODO: better indexing (indexOf finds first)
    const index: number = this.errorMessagesService.messages.indexOf(message);
    this.errorMessagesService.messages.splice(index, 1);
  }
}

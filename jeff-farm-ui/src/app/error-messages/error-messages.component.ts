import { Component } from '@angular/core';
import { ErrorMessagesService } from './error-messages.service';

@Component({
  selector: 'error-messages',
  templateUrl: './error-messages.component.html',
  styleUrls: ['./error-messages.component.css']
})
export class ErrorMessagesComponent {

  constructor(public errorMessagesService: ErrorMessagesService) { }
}

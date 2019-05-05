import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorMessagesComponent } from './error-messages.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    ErrorMessagesComponent,
  ],
  imports: [
    CommonModule,
    NgbModule,
  ],
  exports: [
    ErrorMessagesComponent,
    NgbModule,
  ],
})
export class ErrorMessagesModule { }

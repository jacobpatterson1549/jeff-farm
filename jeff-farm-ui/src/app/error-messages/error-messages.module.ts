import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ErrorMessagesComponent } from './error-messages.component';

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

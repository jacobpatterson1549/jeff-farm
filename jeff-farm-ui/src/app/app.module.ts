import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found.component';
import { GlobalProviders } from './global-providers';
import { ErrorMessagesComponent } from './error-messages/error-messages.component';

@NgModule({
  providers: [
    GlobalProviders,
  ],
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    ErrorMessagesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

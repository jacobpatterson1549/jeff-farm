import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found.component';
import { GlobalProviders } from './global-providers';
import { ErrorMessagesModule } from './error-messages/error-messages.module';

@NgModule({
  providers: [
    GlobalProviders,
  ],
  declarations: [
    AppComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ErrorMessagesModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

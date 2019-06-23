import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ServiceWorkerModule } from '@angular/service-worker';

import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorMessagesModule } from './error-messages/error-messages.module';
import { FooterModule } from './footer/footer.module';
import { GlobalProviders } from './global-providers';
import { HeaderComponent } from './header/header.component';
import { PageNotFoundComponent } from './page-not-found.component';

@NgModule({
  providers: [
    GlobalProviders,
  ],
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FooterModule, // keep before footer module
    AppRoutingModule,
    ErrorMessagesModule,
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

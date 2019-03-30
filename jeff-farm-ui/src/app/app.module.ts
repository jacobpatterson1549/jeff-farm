import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found.component';
import { AuthModule } from './auth/auth.module';
import { HostInterceptor } from './host-interceptor';
import { AuthInterceptor } from './auth/auth-interceptor';
import { JsonInterceptor } from './json-interceptor';
import { ErrorsHandler } from './errors-handler';
import { CrudModule } from './crud/crud.module';
import { UserModule } from './user/user.module';
import { FarmsModule } from './farms/farms.module';
import { HivesModule } from './hives/hives.module';
import { HiveInspectionsModule } from './hive-inspections/hive-inspections.module';

@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HostInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: JsonInterceptor, multi: true },
    { provide: ErrorHandler, useClass: ErrorsHandler },
  ],
  declarations: [
    AppComponent,
    PageNotFoundComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    // UserModule,
    // CrudModule,
    // FarmsModule,
    // HivesModule,
    // HiveInspectionsModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

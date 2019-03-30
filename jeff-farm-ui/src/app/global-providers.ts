import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HostInterceptor } from './host-interceptor';
import { AuthInterceptor } from './auth/auth-interceptor';
import { JsonInterceptor } from './json-interceptor';
import { ErrorHandler } from '@angular/core';
import { ErrorsHandler } from './errors-handler';


export const GlobalProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: HostInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: JsonInterceptor, multi: true },
    { provide: ErrorHandler, useClass: ErrorsHandler },
];
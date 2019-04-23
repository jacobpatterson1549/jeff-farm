import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HostInterceptor } from './host-interceptor';
import { AuthInterceptor } from './auth/auth-interceptor';
import { ErrorHandler } from '@angular/core';
import { ErrorMessagesHandler } from './error-messages/errorMessages-handler';
import { CachingInterceptor } from './caching-interceptor';

export const GlobalProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: HostInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: CachingInterceptor, multi: true }, // should be last interceptor
    { provide: ErrorHandler, useClass: ErrorMessagesHandler },
];
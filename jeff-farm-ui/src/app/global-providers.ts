import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorHandler } from '@angular/core';

import { AuthInterceptor } from './auth/auth-interceptor';
import { CachingInterceptor } from './caching-interceptor';
import { ErrorMessagesHandler } from './error-messages/errorMessages-handler';
import { HostInterceptor } from './host-interceptor';

export const GlobalProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: CachingInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HostInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: ErrorHandler, useClass: ErrorMessagesHandler },
];

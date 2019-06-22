import { ErrorHandler } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { HostInterceptor } from './host-interceptor';
import { AuthInterceptor } from './auth/auth-interceptor';
import { CachingInterceptor } from './caching-interceptor';
import { ErrorMessagesHandler } from './error-messages/errorMessages-handler';

export const GlobalProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: HostInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: CachingInterceptor, multi: true }, // should be last interceptor
    { provide: ErrorHandler, useClass: ErrorMessagesHandler },
];

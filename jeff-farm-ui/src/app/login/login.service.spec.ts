import { of, throwError } from 'rxjs';

import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

import { AuthService } from '../auth/auth.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { LoginService } from './login.service';

describe('LoginService', () => {
    let authServiceSpy: AuthService;
    let errorMessagesServiceSpy: ErrorMessagesService;
    let httpClientSpy: { get: jasmine.Spy };
    let loginService: LoginService;
    beforeEach(() => {
        authServiceSpy = jasmine.createSpyObj('AuthService', ['clearCredentials']);
        errorMessagesServiceSpy = jasmine.createSpyObj('ErrorMessagesService', ['handleError']);
        httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
        loginService = new LoginService(
            authServiceSpy,
            errorMessagesServiceSpy,
            httpClientSpy as any);
    });

    it('should clear the credentials on a successful logout', () => {
        httpClientSpy.get.and.returnValue(of(new HttpResponse({})));
        loginService.logout()
            .subscribe(
                success => expect(authServiceSpy.clearCredentials).toHaveBeenCalled(),
                error => fail('expected logout success'));
        expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
    });

    it('should clear the credentials on a failing logout', () => {
        httpClientSpy.get.and.returnValue(throwError(new HttpErrorResponse({})));
        loginService.logout()
            .subscribe(
                success => fail('expected logout failure'),
                error => {
                    expect(errorMessagesServiceSpy.handleError).toHaveBeenCalled();
                    expect(authServiceSpy.clearCredentials).toHaveBeenCalled();
                });
        expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
    });
});

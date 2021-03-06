import { CachingService } from '../caching.service';
import { LoginSuccess } from '../login/login-success';
import { AuthService } from './auth.service';

describe('AuthService', () => {
    let authService: AuthService;
    let mockCachingService: CachingService;
    beforeEach(() => {
        localStorage.clear();
        mockCachingService = { clear() { } } as CachingService;
        authService = new AuthService(mockCachingService);
    });

    it('should not be an admin user before logging in', () => {
        const isAdminUser: boolean = authService.isAdminUser();
        expect(isAdminUser).toBeFalsy();
    });

    it('should be an admin user if one is provided', () => {
        const loginSuccess: LoginSuccess = { adminUser: true, userId: 1, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        const isAdminUser: boolean = authService.isAdminUser();
        expect(isAdminUser).toBeTruthy();
    });

    it('should not be an admin user if one is not provided.', () => {
        const loginSuccess: LoginSuccess = { adminUser: false, userId: 1, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        const isAdminUser: boolean = authService.isAdminUser();
        expect(isAdminUser).toBeFalsy();
    });

    it('should not be an admin user after logging out', () => {
        const loginSuccess: LoginSuccess = { adminUser: true, userId: 1, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        authService.clearCredentials();
        const isAdminUser: boolean = authService.isAdminUser();
        expect(isAdminUser).toBeFalsy();
    });

    it('should not be logged in before logging in', () => {
        const isLoggedIn: boolean = authService.isLoggedIn();
        expect(isLoggedIn).toBeFalsy();
    });

    it('should be logged in after logging in', () => {
        const loginSuccess: LoginSuccess = { adminUser: false, userId: 1, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        const isLoggedIn: boolean = authService.isLoggedIn();
        expect(isLoggedIn).toBeTruthy();
    });

    it('should not be logged in after logging out', () => {
        const loginSuccess: LoginSuccess = { adminUser: false, userId: 198, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        authService.clearCredentials();
        const isLoggedIn: boolean = authService.isLoggedIn();
        expect(isLoggedIn).toBeFalsy();
    });

    it('should not have a user id before logging in', () => {
        const userId: string = authService.getUserId();
        expect(userId).toBeNull();
    });

    it('should not have a user id before logging in', () => {
        const loginSuccess: LoginSuccess = { adminUser: false, userId: 198, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        const userId: string = authService.getUserId();
        expect(userId).toBe('198');
    });

    it('should not have a user id after logging out', () => {
        const loginSuccess: LoginSuccess = { adminUser: false, userId: 198, jsessionId: 'xyz' };
        authService.setLoggedIn(loginSuccess);
        authService.clearCredentials();
        const userId: string = authService.getUserId();
        expect(userId).toBeNull();
    });

    it('should clear the cache when clearing credentials', () => {
        spyOn(mockCachingService, 'clear');
        authService.clearCredentials();
        expect(mockCachingService.clear).toHaveBeenCalled();
    });
});

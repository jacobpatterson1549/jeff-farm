import { async, TestBed } from '@angular/core/testing';
import {APP_BASE_HREF} from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorMessagesModule } from './error-messages/error-messages.module';
import { FooterModule } from './footer/footer.module';
import { HeaderComponent } from './header/header.component';
import { PageNotFoundComponent } from './page-not-found.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [
        { provide: APP_BASE_HREF, useValue: '/'},
      ],
      declarations: [
        AppComponent,
        PageNotFoundComponent,
        HeaderComponent,
      ],
      imports: [
        AppRoutingModule,
        ErrorMessagesModule,
        FooterModule,
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toEqual('Just Effective Farm Facilitation');
  });
});

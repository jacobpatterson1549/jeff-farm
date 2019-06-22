import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: `app.component.html`
})
export class AppComponent {

  private readonly SERVER_URL: string = environment.SERVER_URL;

  constructor(private sanitizer: DomSanitizer) { }

  getServerURL(): any {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.SERVER_URL);
  }
}

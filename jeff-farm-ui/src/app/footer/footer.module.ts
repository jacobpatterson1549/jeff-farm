import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AboutComponent } from './about/about.component';
import { FooterRoutingModule } from './footer-routing.module';
import { FooterComponent } from './footer.component';
import { HelpComponent } from './help/help.component';

@NgModule({
  declarations: [
    FooterComponent,
    HelpComponent,
    AboutComponent,
  ],
  imports: [
    CommonModule,
    FooterRoutingModule,
    FontAwesomeModule,
  ],
  exports: [
    FooterComponent,
  ],
})
export class FooterModule { }

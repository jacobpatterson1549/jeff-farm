import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AboutComponent } from './about/about.component';
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
  ],
  exports: [
    FooterComponent,
  ],
})
export class FooterModule { }

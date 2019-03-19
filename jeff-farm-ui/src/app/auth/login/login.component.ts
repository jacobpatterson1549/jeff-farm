import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private username: string; // TODO: auto-fill if recently logged out (fetch query param in OnInit{})
  private password: string;

  constructor(
      private authService: AuthService,
      private router: Router) {

    this.username = 'jacob';
    this.password = 'password1';
    if (authService.isLoggedIn) {
      router.navigate(['/user']);
    }
  }

  submitForm() {
    this.authService.login(this.username, this.password)
      .subscribe(sessionId => {
         this.router.navigate(['/farms']);
      });
  }
}

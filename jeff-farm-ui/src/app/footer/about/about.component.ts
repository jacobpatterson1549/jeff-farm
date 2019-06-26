import { Component } from '@angular/core';
import { faGithubSquare, faLinkedin } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
})
export class AboutComponent {
  userLinkedinUrl = 'https://www.linkedin.com/in/jacobpatterson1549/';
  userGithubUrl = 'https://github.com/jacobpatterson1549';
  projectUrl = 'https://github.com/jacobpatterson1549/jeff-farm';
  userLinkedinIcon = faLinkedin;
  userGithubIcon = faGithubSquare;
}

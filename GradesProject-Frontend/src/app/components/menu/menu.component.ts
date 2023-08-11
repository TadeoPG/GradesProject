import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent {
  constructor(private router: Router) {}

  redirectToStudents() {
    this.router.navigate(['/students']);
  }

  redirectToCourses() {
    this.router.navigate(['/courses']);
  }

  redirectToClassrooms() {
    this.router.navigate(['/classrooms']);
  }

  redirectToGrades() {
    this.router.navigate(['/grades']);
  }
}

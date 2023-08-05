import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  constructor(private router: Router) {

  }

  redirigirToAlumnos() {
    this.router.navigate(['/alumnos']);
  }

  redirigirToCursos() {
    this.router.navigate(['/cursos']);
  }

  redirigirToAulas() {
    this.router.navigate(['/aulas']);
  }

  redirigirToCalificaciones() {
    this.router.navigate(['/calificaciones']);
  }

}

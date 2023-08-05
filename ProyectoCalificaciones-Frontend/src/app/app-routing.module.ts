import { CursComponent } from './components/curs/curs.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { AlumnoComponent } from './components/alumno/alumno.component';
import { AulaComponent } from './components/aula/aula.component';
import { CalificacionComponent } from './components/calificacion/calificacion.component';

const routes: Routes = [
  { path: '', component: MenuComponent },
  { path: 'alumnos', component: AlumnoComponent },
  { path: 'cursos', component: CursComponent },
  { path: 'aulas', component: AulaComponent },
  { path: 'calificaciones', component: CalificacionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

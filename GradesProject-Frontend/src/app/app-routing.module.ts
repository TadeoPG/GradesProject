import { CourseComponent } from './components/course/course.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { StudentComponent } from './components/student/student.component';
import { ClassroomComponent } from './components/classroom/classroom.component';
import { GradeComponent } from './components/grade/grade.component';

const routes: Routes = [
  { path: '', component: MenuComponent },
  { path: 'students', component: StudentComponent },
  { path: 'courses', component: CourseComponent },
  { path: 'classrooms', component: ClassroomComponent },
  { path: 'grades', component: GradeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

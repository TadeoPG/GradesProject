import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course/course.service';

@Component({
  selector: 'app-curs',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css'],
})
export class CourseComponent {
  courseForm: FormGroup = new FormGroup({});
  courses: any;

  constructor(
    public fb: FormBuilder,
    public courseService: CourseService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.courseForm = this.fb.group({
      idCourse: [''],
      name: ['', Validators.required],
    });

    this.courseService.getAllCourses().subscribe(
      (resp) => {
        this.courses = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  back(): void {
    this.router.navigate(['/students']);
  }

  next(): void {
    this.router.navigate(['/classrooms']);
  }

  save(): void {
    this.courseService.save(this.courseForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.courseForm.reset();
        this.courses = this.courses.filter(
          (course: { idCourse: any }) => resp.idCourse !== course.idCourse
        );
        //Esto inserta visualmente los datos al final de la tabla
        //Pero no conviene par los updates ya que si actualizamos el primer elemento
        //Lo llevará hacia el último a menos que actualizamos la página cosa que no se desea
        // this.cargos.push(resp);
        //Este método lee todos los datos de la base de datos y los inserta normalmente
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  private loadDataInTable(): void {
    this.courseService.getAllCourses().subscribe(
      (resp) => {
        this.courses = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  delete(course: any) {
    this.courseService.delete(course.idCourse).subscribe(
      (resp) => {
        console.log(resp);
        this.courses.pop(course);
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  update(course: any) {
    this.courseForm.setValue({
      idCourse: course.idCourse,
      name: course.name,
    });
  }
}

import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentService } from 'src/app/services/student/student.service';
import { ClassroomService } from 'src/app/services/classroom/classroom.service';
import { GradeService } from 'src/app/services/grade/grade.service';
import { CourseService } from 'src/app/services/course/course.service';

@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.css'],
})
export class GradeComponent {
  gradeForm: FormGroup = new FormGroup({});
  grades: any;
  students: any;
  courses: any;
  classrooms: any;

  constructor(
    public fb: FormBuilder,
    public gradeService: GradeService,
    public studentService: StudentService,
    public courseService: CourseService,
    public classroomService: ClassroomService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.gradeForm = this.fb.group({
      idGrade: [''],
      student: ['', Validators.required],
      course: ['', Validators.required],
      classroom: ['', Validators.required],
      bimester: ['', Validators.required],
      exam1: ['', Validators.required],
      exam2: ['', Validators.required],
      homeworks: ['', Validators.required],
      finalExam: ['', Validators.required],
    });

    this.gradeService.getAllGrades().subscribe(
      (resp) => {
        this.grades = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.studentService.getAllStudents().subscribe(
      (resp) => {
        this.students = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.courseService.getAllCourses().subscribe(
      (resp) => {
        this.courses = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.classroomService.getAllClassrooms().subscribe(
      (resp) => {
        this.classrooms = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  back(): void {
    this.router.navigate(['/classrooms']);
  }

  save(): void {
    this.gradeService.save(this.gradeForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.gradeForm.reset();
        this.grades = this.grades.filter(
          (grade: { idGrade: any }) => resp.idGrade !== grade.idGrade
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
    this.gradeService.getAllGrades().subscribe(
      (resp) => {
        this.grades = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  delete(grade: any) {
    this.gradeService.delete(grade.idGrade).subscribe(
      (resp) => {
        console.log(resp);
        this.grades.pop(grade);
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  update(grade: any) {
    // let alu = this.alumnos.filter(
    //   (alumno: { idAlumno: any }) =>
    //     alumno.idAlumno === calificacion.alumno.idAlumno
    // )[0];
    this.gradeForm.setValue({
      idGrade: grade.idGrade,
      student: this.students.filter(
        (student: { idStudent: any }) =>
          student.idStudent === grade.student.idStudent
      )[0],
      //Esto es en caso que solo querramos enviar al [ngValue] los nombres y apellidos
      // alumno: alu.nombres + " " + alu.apellidos,
      curso: this.courses.filter(
        (course: { idCourse: any }) => course.idCourse === grade.course.idCourse
      )[0],
      classrooms: this.classrooms.filter(
        (classroom: { idClassroom: any }) =>
          classroom.idClassroom === grade.classroom.idClassroom
      )[0],
      bimester: grade.bimester,
      exam1: grade.exam1,
      exam2: grade.exam2,
      homeworks: grade.homeworks,
      finalExam: grade.finalExam,
    });

    console.log(this.gradeForm.value);
  }
}

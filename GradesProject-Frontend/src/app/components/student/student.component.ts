import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentService } from 'src/app/services/student/student.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css'],
})
export class StudentComponent implements OnInit {
  studentForm: FormGroup = new FormGroup({});
  students: any;

  constructor(
    public fb: FormBuilder,
    public studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.studentForm = this.fb.group({
      idStudent: [''],
      names: ['', Validators.required],
      lastNames: ['', Validators.required],
    });

    this.studentService.getAllStudents().subscribe(
      (resp) => {
        this.students = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  back(): void {
    this.router.navigate(['']);
  }

  next(): void {
    this.router.navigate(['/courses']);
  }

  save(): void {
    this.studentService.save(this.studentForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.studentForm.reset();
        this.students = this.students.filter(
          (student: { idStudent: any }) => resp.idStudent !== student.idStudent
        );
        //Esto inserta visualmente los datos al final de la tabla
        //Pero no conviene par los updates ya que si actualizamos el primer elemento
        //Lo llevará hacia el último a menos que actualizamos la página cosa que no se desea
        // this.cargos.push(resp);
        //Este método lee todos los datos de la base de datos y los inserta normalmente
        //NO ES EFICIENTE EN RENDIMIENTO
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  private loadDataInTable(): void {
    this.studentService.getAllStudents().subscribe(
      (resp) => {
        this.students = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  delete(student: any) {
    this.studentService.delete(student.idStudent).subscribe(
      (resp) => {
        console.log(resp);
        this.students.pop(student);
        //El propósito es similar al uso en el método guardar()
        //El problema es que sin esto, se elimina visualmente la última fila
        //Este método lee otra vez todos los datos de la base de datos y los posiciona ordenadamente
        //PERO NO ES EFICIENTE EN RENDIMIENTO
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  update(student: any) {
    this.studentForm.setValue({
      idStudent: student.idStudent,
      names: student.names,
      lastNames: student.lastNames,
    });
  }
}

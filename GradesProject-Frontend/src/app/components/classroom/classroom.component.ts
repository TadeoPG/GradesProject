import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClassroomService } from 'src/app/services/classroom/classroom.service';

@Component({
  selector: 'app-aula',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.css'],
})
export class ClassroomComponent {
  classroomForm: FormGroup = new FormGroup({});
  classrooms: any;

  constructor(
    public fb: FormBuilder,
    public classroomService: ClassroomService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.classroomForm = this.fb.group({
      idClassroom: [''],
      level: ['', Validators.required],
      section: ['', Validators.required],
    });

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
    this.router.navigate(['/courses']);
  }

  next(): void {
    this.router.navigate(['/grades']);
  }

  save(): void {
    this.classroomService.save(this.classroomForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.classroomForm.reset();
        this.classrooms = this.classrooms.filter(
          (classroom: { idClassroom: any }) =>
            resp.idClassroom !== classroom.idClassroom
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

  delete(classroom: any) {
    this.classroomService.delete(classroom.idClassroom).subscribe(
      (resp) => {
        console.log(resp);
        this.classrooms.pop(classroom);
        this.loadDataInTable();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  update(classroom: any) {
    this.classroomForm.setValue({
      idClassroom: classroom.idClassroom,
      level: classroom.level,
      section: classroom.section,
    });
  }
}

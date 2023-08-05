import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlumnoService } from 'src/app/service/alumno/alumno.service';

@Component({
  selector: 'app-alumno',
  templateUrl: './alumno.component.html',
  styleUrls: ['./alumno.component.css'],
})
export class AlumnoComponent implements OnInit {
  alumnoForm: FormGroup = new FormGroup({});
  alumnos: any;

  constructor(
    public fb: FormBuilder,
    public alumnoService: AlumnoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.alumnoForm = this.fb.group({
      idAlumno: [''],
      nombres: ['', Validators.required],
      apellidos: ['', Validators.required],
    });

    this.alumnoService.getAllAlumnos().subscribe(
      (resp) => {
        this.alumnos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  volver(): void {
    this.router.navigate(['']);
  }

  siguiente(): void {
    this.router.navigate(['/cursos']);
  }

  guardar(): void {
    this.alumnoService.save(this.alumnoForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.alumnoForm.reset();
        this.alumnos = this.alumnos.filter(
          (alumno: { idAlumno: any }) => resp.idAlumno !== alumno.idAlumno
        );
        //Esto inserta visualmente los datos al final de la tabla
        //Pero no conviene par los updates ya que si actualizamos el primer elemento
        //Lo llevará hacia el último a menos que actualizamos la página cosa que no se desea
        // this.cargos.push(resp);
        //Este método lee todos los datos de la base de datos y los inserta normalmente
        //NO ES EFICIENTE EN RENDIMIENTO
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  private cargarDatosEnTabla(): void {
    this.alumnoService.getAllAlumnos().subscribe(
      (resp) => {
        this.alumnos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  eliminar(alumno: any) {
    this.alumnoService.delete(alumno.idAlumno).subscribe(
      (resp) => {
        console.log(resp);
        this.alumnos.pop(alumno);
        //El propósito es similar al uso en el método guardar()
        //El problema es que sin esto, se elimina visualmente la última fila
        //Este método lee otra vez todos los datos de la base de datos y los posiciona ordenadamente
        //PERO NO ES EFICIENTE EN RENDIMIENTO
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  actualizar(alumno: any) {
    this.alumnoForm.setValue({
      idAlumno: alumno.idAlumno,
      nombres: alumno.nombres,
      apellidos: alumno.apellidos,
    });
  }
}

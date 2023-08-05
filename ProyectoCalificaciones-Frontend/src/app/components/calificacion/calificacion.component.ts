import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlumnoService } from 'src/app/service/alumno/alumno.service';
import { AulaService } from 'src/app/service/aula/aula.service';
import { CalificacionService } from 'src/app/service/calificacion/calificacion.service';
import { CursoService } from 'src/app/service/curso/curso.service';

@Component({
  selector: 'app-calificacion',
  templateUrl: './calificacion.component.html',
  styleUrls: ['./calificacion.component.css'],
})
export class CalificacionComponent {
  calificacionForm: FormGroup = new FormGroup({});
  calificaciones: any;
  alumnos: any;
  cursos: any;
  aulas: any;

  constructor(
    public fb: FormBuilder,
    public calificacionService: CalificacionService,
    public alumnoService: AlumnoService,
    public cursoService: CursoService,
    public aulaService: AulaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.calificacionForm = this.fb.group({
      idCalificacion: [''],
      alumno: ['', Validators.required],
      curso: ['', Validators.required],
      aula: ['', Validators.required],
      bimestre: ['', Validators.required],
      examen1: ['', Validators.required],
      examen2: ['', Validators.required],
      tareas: ['', Validators.required],
      examenFinal: ['', Validators.required],
    });

    this.calificacionService.getAllCalificaciones().subscribe(
      (resp) => {
        this.calificaciones = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.alumnoService.getAllAlumnos().subscribe(
      (resp) => {
        this.alumnos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.cursoService.getAllCursos().subscribe(
      (resp) => {
        this.cursos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );

    this.aulaService.getAllAulas().subscribe(
      (resp) => {
        this.aulas = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  volver(): void {
    this.router.navigate(['/aulas']);
  }

  guardar(): void {
    this.calificacionService.save(this.calificacionForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.calificacionForm.reset();
        this.calificaciones = this.calificaciones.filter(
          (calificacion: { idCalificacion: any }) =>
            resp.idCalificacion !== calificacion.idCalificacion
        );
        //Esto inserta visualmente los datos al final de la tabla
        //Pero no conviene par los updates ya que si actualizamos el primer elemento
        //Lo llevará hacia el último a menos que actualizamos la página cosa que no se desea
        // this.cargos.push(resp);
        //Este método lee todos los datos de la base de datos y los inserta normalmente
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  private cargarDatosEnTabla(): void {
    this.calificacionService.getAllCalificaciones().subscribe(
      (resp) => {
        this.calificaciones = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  eliminar(calificacion: any) {
    this.calificacionService.delete(calificacion.idCalificacion).subscribe(
      (resp) => {
        console.log(resp);
        this.calificaciones.pop(calificacion);
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  actualizar(calificacion: any) {
    // let alu = this.alumnos.filter(
    //   (alumno: { idAlumno: any }) =>
    //     alumno.idAlumno === calificacion.alumno.idAlumno
    // )[0];
    this.calificacionForm.setValue({
      idCalificacion: calificacion.idCalificacion,
      alumno: this.alumnos.filter(
        (alumno: { idAlumno: any }) =>
          alumno.idAlumno === calificacion.alumno.idAlumno
      )[0],
      //Esto es en caso que solo querramos enviar al [ngValue] los nombres y apellidos
      // alumno: alu.nombres + " " + alu.apellidos,
      curso: this.cursos.filter(
        (curso: { idCurso: any }) =>
          curso.idCurso === calificacion.curso.idCurso
      )[0],
      aula: this.aulas.filter(
        (aula: { idAula: any }) => aula.idAula === calificacion.aula.idAula
      )[0],
      bimestre: calificacion.bimestre,
      examen1: calificacion.examen1,
      examen2: calificacion.examen2,
      tareas: calificacion.tareas,
      examenFinal: calificacion.examenFinal,
    });

    console.log(this.calificacionForm.value);
  }
}

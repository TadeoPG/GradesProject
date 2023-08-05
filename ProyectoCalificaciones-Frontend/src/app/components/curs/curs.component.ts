import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AulaService } from 'src/app/service/aula/aula.service';
import { CursoService } from 'src/app/service/curso/curso.service';

@Component({
  selector: 'app-curs',
  templateUrl: './curs.component.html',
  styleUrls: ['./curs.component.css'],
})
export class CursComponent {
  cursoForm: FormGroup = new FormGroup({});
  cursos: any;

  constructor(
    public fb: FormBuilder,
    public cursoService: CursoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cursoForm = this.fb.group({
      idCurso: [''],
      nombre: ['', Validators.required],
    });

    this.cursoService.getAllCursos().subscribe(
      (resp) => {
        this.cursos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  volver(): void {
    this.router.navigate(['/alumnos']);
  }

  siguiente(): void { 
this.router.navigate(['/aulas']);
  }

  guardar(): void {
    this.cursoService.save(this.cursoForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.cursoForm.reset();
        this.cursos = this.cursos.filter(
          (curso: { idCurso: any }) => resp.idCurso !== curso.idCurso
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
    this.cursoService.getAllCursos().subscribe(
      (resp) => {
        this.cursos = resp;
        console.log(resp);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  eliminar(curso: any) {
    this.cursoService.delete(curso.idCurso).subscribe(
      (resp) => {
        console.log(resp);
        this.cursos.pop(curso);
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  actualizar(curso: any) {
    this.cursoForm.setValue({
      idCurso: curso.idCurso,
      nombre: curso.nombre,
    });
  }
}

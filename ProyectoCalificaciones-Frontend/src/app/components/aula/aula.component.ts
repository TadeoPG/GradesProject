import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlumnoService } from 'src/app/service/alumno/alumno.service';
import { AulaService } from 'src/app/service/aula/aula.service';

@Component({
  selector: 'app-aula',
  templateUrl: './aula.component.html',
  styleUrls: ['./aula.component.css'],
})
export class AulaComponent {
  aulaForm: FormGroup = new FormGroup({});
  aulas: any;

  constructor(
    public fb: FormBuilder,
    public aulaService: AulaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.aulaForm = this.fb.group({
      idAula: [''],
      nivel: ['', Validators.required],
      seccion: ['', Validators.required],
    });

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
    this.router.navigate(['/cursos']);
  }

  siguiente(): void {
    this.router.navigate(['/calificaciones']);
  }

  guardar(): void {
    this.aulaService.save(this.aulaForm.value).subscribe(
      (resp) => {
        console.log(resp);
        this.aulaForm.reset();
        this.aulas = this.aulas.filter(
          (alumno: { idAula: any }) => resp.idAula !== alumno.idAula
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

  eliminar(aula: any) {
    this.aulaService.delete(aula.idAula).subscribe(
      (resp) => {
        console.log(resp);
        this.aulas.pop(aula);
        this.cargarDatosEnTabla();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  actualizar(aula: any) {
    this.aulaForm.setValue({
      idAula: aula.idAula,
      nivel: aula.nivel,
      seccion: aula.seccion,
    });
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AlumnoService {
  private API_SERVER = 'http://localhost:8080/alumnos';

  constructor(private httpClient: HttpClient) {}

  public getAllAlumnos(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(alumno: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, alumno);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, alumno: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, alumno);
  }
}

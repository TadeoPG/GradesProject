import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CalificacionService {
  private API_SERVER = 'http://localhost:8080/calificaciones';

  constructor(private httpClient: HttpClient) {}

  public getAllCalificaciones(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(calificacion: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, calificacion);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, calificacion: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, calificacion);
  }
}

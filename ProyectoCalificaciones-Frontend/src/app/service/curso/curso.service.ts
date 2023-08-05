import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CursoService {
  private API_SERVER = 'http://localhost:8080/cursos';

  constructor(private httpClient: HttpClient) {}

  public getAllCursos(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(curso: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, curso);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, curso: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, curso);
  }
}

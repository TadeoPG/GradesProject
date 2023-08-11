import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GradeService {
  private API_SERVER = 'http://localhost:8080/grades';

  constructor(private httpClient: HttpClient) {}

  public getAllGrades(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(grade: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, grade);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, grade: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, grade);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private API_SERVER = 'http://localhost:8080/students';

  constructor(private httpClient: HttpClient) {}

  public getAllStudents(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(student: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, student);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, student: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, student);
  }
}

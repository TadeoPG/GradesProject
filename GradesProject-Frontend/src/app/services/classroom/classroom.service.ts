import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClassroomService {
  private API_SERVER = 'http://localhost:8080/classrooms';

  constructor(private httpClient: HttpClient) {}

  public getAllClassrooms(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(classroom: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, classroom);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, classroom: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, classroom);
  }
}

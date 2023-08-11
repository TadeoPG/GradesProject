import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private API_SERVER = 'http://localhost:8080/courses';

  constructor(private httpClient: HttpClient) {}

  public getAllCourses(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(course: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, course);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, course: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, course);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AulaService {

  private API_SERVER = 'http://localhost:8080/aulas';

  constructor(private httpClient: HttpClient) {}

  public getAllAulas(): Observable<any> {
    return this.httpClient.get(this.API_SERVER);
  }

  public save(aula: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER, aula);
  }

  public delete(id: any): Observable<any> {
    return this.httpClient.delete(this.API_SERVER + '/' + id);
  }

  public update(id: any, aula: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + id, aula);
  }
}

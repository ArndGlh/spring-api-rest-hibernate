import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ConfigService {
  constructor(private http: HttpClient) { }

  getGamesFromServer() {
    return this.http.get('http://localhost:8080/api/rest/v1/task/progress');
  }
}
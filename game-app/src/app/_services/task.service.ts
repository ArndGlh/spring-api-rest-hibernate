import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, Subject} from 'rxjs';
import { Task } from '../_models/Task.model';

@Injectable()
export class TaskService {
    
    readonly BASE_URL = 'http://localhost:8080/api/rest/v1/';
    readonly httpOptions = {headers: new HttpHeaders({'Content-Type':'application/json'})};
    
    constructor(private httpClient: HttpClient){}

    getTasksByGameId(id: number) : Observable <Task[]> {
      return this.httpClient.get<Task[]>(this.BASE_URL+'task/game/'+id);
    }
}
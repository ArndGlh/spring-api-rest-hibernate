import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Progress } from '../_models/Progress.model';
import { Task } from '../_models/Task.model';
import { TaskService } from '../_services/task.service';
import { map } from 'rxjs/operators';

@Injectable()
export class ProgressService {
    
    readonly BASE_URL = 'http://localhost:8080/api/rest/v1/';
    readonly httpOptions = {headers: new HttpHeaders({'Content-Type':'application/json'})};
    
    constructor(private httpClient: HttpClient, private taskService: TaskService){}

    getProgressByUserAndGame(userId: number, gameId: number) : Observable <Progress[]> {
       return this.httpClient.get<Progress[]>(this.BASE_URL+'progress/user/'+userId+'/'+gameId); 
    }

    addGameToUserXXXXXXXX(userId: number, gameId: number){    
        var bol: Boolean = false;
        this.getTasks(gameId).subscribe((res: Task[]) => {
            console.log("Tasks : "+res[0][0]);
            res.forEach(function(element) {
                console.log('element : '+element);
                this.addProgressToUser(userId, element.id).subscribe((res: Boolean) =>{
                    console.log('bol avant : '+bol);
                    bol = res && bol;
                    console.log('bol après : '+bol);
                });
            });
        });
        return bol;
    }

    getTasks(id: number): Observable<Task[]>{
        return this.taskService.getTasksByGameId(+id);
    }

    addProgressToUser(userId: number, taskId: number){
        let progress: Progress;
        progress.actual_progress = 0;
        progress.task_id = taskId;
        progress.user_id = userId;

        this.httpClient
        .post(this.BASE_URL+'progress', progress, this.httpOptions)
        .subscribe(
          () => {
            console.log('Enregistrement terminé !');
            return true;
          },
          (error) => {
            console.log('Erreur ! : ' + error);
            return false;
          }
      );
    }

    addGameToUser(userId: number, gameId: number){   
        this.httpClient.post<any>('http://localhost:8080/api/rest/v1/progress/', {userId: userId, gameId: gameId} )
        .pipe(map(gameCreated => {
            if (gameCreated) {
                console.log(gameCreated);
            }
            return gameCreated;
        }));
    }
}
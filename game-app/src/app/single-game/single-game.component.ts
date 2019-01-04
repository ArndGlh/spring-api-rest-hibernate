import { Component, OnInit, OnDestroy } from '@angular/core';
import { GameService } from '../_services/game.service';
import { TaskService } from '../_services/task.service';
import { ProgressService } from '../_services/progress.service';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../_models/Game.model';
import { Task } from '../_models/Task.model';
import { Progress } from '../_models/Progress.model';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs/';
import {fromEvent} from 'rxjs';

@Component({
  selector: 'app-single-game',
  templateUrl: './single-game.component.html'
})
export class SingleGameComponent implements OnInit {

  public game: Game[];
  public tasks: Task[];
  public progress: Progress[];
  public globalProgress: number;
  public id: any; // Game id
  
  constructor(private gameService: GameService, 
              private route: ActivatedRoute,
              private taskService: TaskService,
              private progressService: ProgressService) { }
  
  ngOnInit() {
    this.id = this.route.snapshot.params['id'];

    this.getGames()
    .subscribe((res: Game[]) => {
      this.game = res;
    });

    this.getTasks()
    .subscribe((res: Task[]) => {
      this.tasks = res;
      this.log(this.tasks);
    });

    this.getProgress()
    .subscribe((res: Progress[]) => {
      this.progress = res;
      this.log(this.progress);
      this.globalProgress = this.calculateGlobalProgress(res);
    });
  }

  log(tab: any[]){
    for(var i=0;i<tab.length;i++){
    console.log(tab[i]);
    }
  }

  getGames(): Observable<Game[]> {
    return this.gameService.getGameById(+this.id);
  }

  getTasks(): Observable<Task[]>{
    return this.taskService.getTasksByGameId(+this.id);
  }

  getProgress(): Observable<Progress[]>{
    return this.progressService.getProgressByUserAndGame(JSON.parse(localStorage.getItem('currentUser')).id, +this.id);
  }

  // TODO trouver un moyen de calculer le progrès global en mettant max progress seulement dans task
  calculateGlobalProgress(progress: Progress[]):number{
    var res = 0;
    progress.forEach(function(element) {
        console.log(element);
        res += element.actual_progress * 100 / element.max_progress;
      });
    return res / progress.length; 
  }
}
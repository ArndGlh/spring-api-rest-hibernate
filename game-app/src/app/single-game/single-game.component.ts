import { Component, OnInit, OnDestroy } from '@angular/core';
import { GameService } from '../_services/game.service';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../_models/Game.model';
import { Task } from '../_models/Task.model';

@Component({
  selector: 'app-single-game',
  templateUrl: './single-game.component.html'
})
export class SingleGameComponent implements OnInit {

  game: Game[];
  tasks: Task[];
  
  constructor(private gameService: GameService, private route: ActivatedRoute) { }
  
  ngOnInit() {
    this.getGames();
    this.getTasks();
  }

  getGames(): void {
    const id = this.route.snapshot.params['id'];
    this.gameService.getGameById(+id)
      .subscribe(game => this.game = game);
      console.log(this.game);
  }

  getTasks(): void{
    const id = this.route.snapshot.params['id'];
    this.gameService.getTasksByGameId(+id)
      .subscribe(task => this.tasks = task);
      console.log(this.tasks);
      
  }

  // ngOnDestroy() {
  //   this.gameSubscription.unsubscribe();
  // }
}
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
  private id: any;
  
  constructor(private gameService: GameService, private route: ActivatedRoute) { }
  
  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.getGames();
    this.getTasks();
  }

  getGames(): void {
    this.gameService.getGameById(+this.id)
      .subscribe(game => this.game = game);
      console.log(this.game);
  }

  getTasks(): void{
    this.gameService.getTasksByGameId(+this.id)
      .subscribe(task => this.tasks = task);
  }
}
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl  } from '@angular/forms';
import { GameService } from '../_services/game.service';
import { Router } from '@angular/router';
import { Game } from '../_models/Game.model';


@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html'
})
export class NewGameComponent implements OnInit {

  searchForm: FormGroup;
  addForm: FormGroup;
  games: Game[];
  gameAdded: any;

  constructor(private gameService: GameService, private router: Router, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.searchForm = new FormGroup({
      search: new FormControl()
    });
    this.addForm = new FormGroup({
      id: new FormControl()
    });
  }

  onSubmit() {
    this.gameService.searchGames(this.searchForm.get('search').value)
      .subscribe(games => this.games = games);
  }

  addGame(gameId: number){
    console.log('current user: '+JSON.parse(localStorage.getItem('currentUser')).id);
    console.log('addform value : '+gameId);
    this.gameService.addGameToUser(JSON.parse(localStorage.getItem('currentUser')).id, gameId)
      .subscribe(response => this.gameAdded = response);
  }
}

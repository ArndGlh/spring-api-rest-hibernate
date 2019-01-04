import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl  } from '@angular/forms';
import { GameService } from '../_services/game.service';
import { Router } from '@angular/router';
import { Game } from '../_models/Game.model';
import { ProgressService } from '../_services';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html'
})
export class NewGameComponent implements OnInit {

  searchForm: FormGroup;
  addForm: FormGroup;
  games: Game[];
  gameAdded: any;
  gameAddedText: string;

  sortTitle = false;
  sortGenre = false;
  sortYear = false;

  constructor(private gameService: GameService, 
    private router: Router, 
    private formBuilder: FormBuilder,
    private progressService: ProgressService) {}

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
    this.gameAdded = this.progressService.addGameToUser(JSON.parse(localStorage.getItem('currentUser')).id, gameId);
    console.log("Game added : "+this.gameAdded);
    if(this.gameAdded){
      this.gameAddedText = "Game added successfully.";
    }else{
      this.gameAddedText = "An error has occured, game not added.";
    }
  }

  public sortByTitle(){
    this.sortTitle = !this.sortTitle;

    if(this.sortTitle){
      this.games.sort(function(a, b){
        if(a[2] < b[2]) return -1;
        if(a[2] > b[2]) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[2] > b[2]) return -1;
        if(a[2] < b[2]) return 1;
        return 0;
      });
    }
  }

  sortByGenre(){
    this.sortGenre = !this.sortGenre;

    if(this.sortGenre){
      this.games.sort(function(a, b){
        if(a[1] < b[1]) return -1;
        if(a[1] > b[1]) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[1] > b[1]) return -1;
        if(a[1] < b[1]) return 1;
        return 0;
      });
    }
  }

  sortByRelease(){
    this.sortYear = !this.sortYear;

    if(this.sortYear){
      this.games.sort(function(a, b){
        if(a[3] < b[3]) return -1;
        if(a[3] > b[3]) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[3] > b[3]) return -1;
        if(a[3] < b[3]) return 1;
        return 0;
      });
    }
  }
}

import { Component, OnDestroy, OnInit, Inject } from '@angular/core';
import { GameService } from '../_services/game.service';
import { Subscription } from 'rxjs/Subscription';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material';

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html'
})
export class GameViewComponent implements OnInit, OnDestroy {
  
  games: any[];
  gameSubscription: Subscription;
  sortTitle = false;
  sortGenre = false;
  sortYear = false;
  sortProgress = false;
  
  lastUpdate = new Promise((resolve, reject) => {
    const date = new Date();
    setTimeout(
      () => {
        resolve(date);
      }, 2000
    );
  });
  
  constructor(private gameService: GameService, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) { 
    iconRegistry.addSvgIcon(
      'thumbs-up',
      sanitizer.bypassSecurityTrustResourceUrl('assets/img/examples/thumbup-icon.svg'));
  }
  
  ngOnInit() {
    this.gameSubscription = this.gameService.gamesSubject.subscribe(
      (games: any[]) => {
        this.games = games;
      }
    );
    this.gameService.getGamesFromServer(JSON.parse(localStorage.getItem('currentUser')).id);
    this.gameService.emitGameSubject();
    
  }
  
  ngOnDestroy() {
    this.gameSubscription.unsubscribe();
  }
  
  sortByTitle(){
    console.log(this.games);
    this.sortTitle = !this.sortTitle;

    if(this.sortTitle){
      this.games.sort(function(a, b){
        if(a[0].title < b[0].title) return -1;
        if(a[0].title > b[0].title) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[0].title > b[0].title) return -1;
        if(a[0].title < b[0].title) return 1;
        return 0;
      });
    }
  }

  sortByGenre(){
    this.sortGenre = !this.sortGenre;

    if(this.sortGenre){
      this.games.sort(function(a, b){
        if(a[0].genre < b[0].genre) return -1;
        if(a[0].genre > b[0].genre) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[0].genre > b[0].genre) return -1;
        if(a[0].genre < b[0].genre) return 1;
        return 0;
      });
    }
  }

  sortByRelease(){
    this.sortYear = !this.sortYear;

    if(this.sortYear){
      this.games.sort(function(a, b){
        if(a[0].year < b[0].year) return -1;
        if(a[0].year > b[0].year) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[0].year > b[0].year) return -1;
        if(a[0].year < b[0].year) return 1;
        return 0;
      });
    }
  }

  sortByProgress(){
    this.sortProgress = !this.sortProgress;

    if(this.sortProgress){
      this.games.sort(function(a, b){
        if(a[1].completion < b[1].completion) return -1;
        if(a[1].completion > b[1].completion) return 1;
        return 0;
      });
    }else{
      this.games.sort(function(a, b){
        if(a[1].completion > b[1].completion) return -1;
        if(a[1].completion < b[1].completion) return 1;
        return 0;
      });
    }
  }
}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.scss']
})
export class GameViewComponent implements OnInit, OnDestroy {
  
  games: any[];
  gameSubscription: Subscription;
  
  lastUpdate = new Promise((resolve, reject) => {
    const date = new Date();
    setTimeout(
      () => {
        resolve(date);
      }, 2000
    );
  });
  
  constructor(private gameService: GameService) { }
  
  ngOnInit() {
    this.gameSubscription = this.gameService.gamesSubject.subscribe(
      (games: any[]) => {
        this.games = games;
      }
    );
    this.gameService.emitGameSubject();
  }

  onSave() {
    this.gameService.saveAppareilsToServer();
  }
  
  ngOnDestroy() {
    this.gameSubscription.unsubscribe();
  }
  
  // onAllumer() {
  //   this.gameService.switchOnAll();
  // }
  
  // onEteindre() {
  //   if(confirm('Etes-vous sûr de vouloir éteindre tous vos appareils ?')) {
  //     this.gameService.switchOffAll();
  //   } else {
  //     return null;
  //   }
  // }
  
}
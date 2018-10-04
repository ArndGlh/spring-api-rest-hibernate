import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-game',
  templateUrl: './single-game.component.html',
  styleUrls: ['./single-game.component.scss']
})
export class SingleGameComponent implements OnInit {
  
  title: string = 'Game';
  genre: string = 'Genre';
  
  constructor(private gameService: GameService, private route: ActivatedRoute) { }
  
  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    var game = this.gameService.getGameById(+id);
    console.log('GAME : '+game);
    this.title = game['title'];
    this.genre = game['genre'];

  }
}
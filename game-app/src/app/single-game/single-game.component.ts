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
    this.title = this.gameService.getGameById(+id).title;
    this.genre = this.gameService.getGameById(+id).genre;
  }
}
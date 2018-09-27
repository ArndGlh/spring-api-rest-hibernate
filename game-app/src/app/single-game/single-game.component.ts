import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-game',
  templateUrl: './single-game.component.html',
  styleUrls: ['./single-game.component.scss']
})
export class SingleGameComponent implements OnInit {
  
  name: string = 'Game';
  status: string = 'Statut';
  
  constructor(private gameService: GameService, private route: ActivatedRoute) { }
  
  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.name = this.gameService.getGameById(+id).name;
    this.status = this.gameService.getGameById(+id).status;
  }
}
import { Component, OnInit, Input } from '@angular/core';
import { GameService } from '../services/game.service'



@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  @Input() gameStatus: string;
  @Input() gameName: string;
  @Input() index: number;
  @Input() id: number;

  constructor(private gameService: GameService) { }

  ngOnInit() {
  }

  getStatus(){
    return this.gameStatus;
  }

  getColor() {
    if(this.gameStatus === 'allumé') {
      return 'green';
    } else if(this.gameStatus === 'éteint') {
      return 'red';
    }
  }

  onSwitch() {
    if(this.gameStatus === 'allumé') {
      this.gameService.switchOffOne(this.index);
    } else if(this.gameStatus === 'éteint') {
      this.gameService.switchOnOne(this.index);
    }
}
}
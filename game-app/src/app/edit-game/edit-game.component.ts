import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GameService } from '../_services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html'
})
export class EditGameComponent implements OnInit {

  defaultOnOff = 'éteint';
  constructor(private gameService: GameService, private router: Router) {

   }

  ngOnInit() {
  }

  onSubmit(form: FormsModule) {
    const name = form['name'];
    const status = form['status'];
    //this.gameService.addGame(name, status);
    this.router.navigate(['/games']);
  }
}

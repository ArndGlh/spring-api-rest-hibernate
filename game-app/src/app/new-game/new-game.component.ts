import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GameService } from '../_services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html'
})
export class NewGameComponent implements OnInit {

  constructor(private gameService: GameService, private router: Router) {

   }

  ngOnInit() {
  }

  onSubmit(form: FormsModule) {
    const id = form['id'];
    this.gameService.addGameToUser(id);
    this.router.navigate(['/games']);
  }
}

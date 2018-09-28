import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GameService } from '../services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-game-component',
  templateUrl: './edit-game-component.component.html',
  styleUrls: ['./edit-game-component.component.scss']
})
export class EditGameComponent implements OnInit {

  defaultOnOff = 'éteint';
  constructor(private gameService: GameService, private router: Router) {

   }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    const name = form.value['name'];
    const status = form.value['status'];
    //this.gameService.addGame(name, status);
    this.router.navigate(['/games']);
  }
}

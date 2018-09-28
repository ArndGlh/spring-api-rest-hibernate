import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  authStatus: boolean;

  constructor(private gameService: GameService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    console.log('mail: '+ form.value['mail']);
    console.log('password: '+ form.value['password']);


    this.authService.signIn().then(
      () => {
        console.log('Sign in successful!');
        this.authStatus = this.authService.isAuth;
        this.router.navigate(['games']);
      }
    );
  }

  // onSubmit(form: NgForm) {
  //   const name = form.value['name'];
  //   const status = form.value['status'];
  //   this.gameService.addGame(name, status);
  //   this.router.navigate(['/games']);
  // }

  onSignOut() {
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
  }
}
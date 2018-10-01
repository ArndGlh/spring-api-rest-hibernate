import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class GameService {
    
    gamesSubject = new Subject<any[]>();
    
    private games: any[];

    constructor(private httpClient: HttpClient){}
    
    emitGameSubject() {
        this.gamesSubject.next(this.games);
    }
    
    getGameById(id: number) {
        const game = this.games.find(
            (s) => {
                return s.id === id;
            }
        );
        return game;
    }
    
    // addGame(title: string, genre: string, year: number) {
    //     const gameObject = {
    //         title: '',
    //         genre: '',
    //         year: 0
    //     };
    //     gameObject.title = title;
    //     gameObject.genre = genre;
    //     gameObject.year = year;
    //     this.games.push(gameObject);
    //     this.emitGameSubject();
    // }

    saveGameToServer() {
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':'application/json'
            })
        };

        this.httpClient
          .post('http://localhost:8080/api/rest/v1/game', this.games, httpOptions)
          
          .subscribe(
            () => {
              console.log('Enregistrement terminé !');
            },
            (error) => {
              console.log('Erreur ! : ' + error);
            }
          );
    }

    getGamesFromServer() {
        this.httpClient
          .get<any[]>('http://localhost:8080/api/rest/v1/game/progress')
          .subscribe(
            (response) => {
              console.log(response);
              this.games = response;
              console.log(this.games);
              this.emitGameSubject();
            },
            (error) => {
              console.log('Erreur ! : ' + error);
            }
          );
    }

    // switchOnAll() {
    //     for(let game of this.games) {
    //         game.status = 'allumé';
    //     }
    //     this.emitGameSubject();
    // }
    
    // switchOffAll() {
    //     for(let game of this.games) {
    //         game.status = 'éteint';
    //         this.emitGameSubject();
    //     }
    // }
    
    // switchOnOne(i: number) {
    //     this.games[i].status = 'allumé';
    //     this.emitGameSubject();
    // }
    
    // switchOffOne(i: number) {
    //     this.games[i].status = 'éteint';
    //     this.emitGameSubject();
    // }
    
}
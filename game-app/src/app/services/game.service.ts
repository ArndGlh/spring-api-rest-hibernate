import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class GameService {
    
    gamesSubject = new Subject<any[]>();
    
    private games = [
        {
            title: "Super Smash Bros Melee",
            genre: "Jeu de combat",
            year: "2001"
        }
    ];

    constructor(private httpClient: HttpClient){}
    
    emitGameSubject() {
        this.gamesSubject.next(this.games.slice());
    }
    
    // getGameById(id: number) {
    //     const game = this.games.find(
    //         (s) => {
    //             return s.id === id;
    //         }
    //     );
    //     return game;
    // }
    
    // addGame(name: string, status: string) {
    //     const gameObject = {
    //         id: 0,
    //         name: '',
    //         status: ''
    //     };
    //     gameObject.name = name;
    //     gameObject.status = status;
    //     gameObject.id = this.games[(this.games.length - 1)].id + 1;
    //     this.games.push(gameObject);
    //     this.emitGameSubject();
    // }

    saveAppareilsToServer() {
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':'application/json'
            })
        };

        var gamesTest =
            {
                "title": "Super Smash Bros Melee",
                "genre": "Jeu de combat",
                "year": "2001"
            };

        this.httpClient
          .post('http://localhost:8080/api/rest/v1/game', gamesTest, httpOptions)
          
          .subscribe(
            () => {
              console.log('Enregistrement terminé !');
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
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, Subject} from 'rxjs';
import { Game } from '../_models/Game.model';
import { Task } from '../_models/Task.model';

@Injectable()
export class GameService {
    
    gamesSubject = new Subject<any[]>();
    private games: any;

    constructor(private httpClient: HttpClient){
    }
    
    emitGameSubject() {
        this.gamesSubject.next(this.games);
    }

    getGamesFromServer(userId: number) {

      this.httpClient
        .get<any[]>('http://localhost:8080/game/progress/'+userId)

        .subscribe(
          (response) => {
            console.log(response);
            this.games = response;
            this.emitGameSubject();
          },
          (error) => {
            console.log('Erreur ! : ' + error);
          }
        );
    }

    // getGameById(id: number) {
    //   this.httpClient
    //   .get<any[]>('http://localhost:8080/game/id/'+id)
    //   .subscribe(
    //     (response) => {
    //       console.log(response);
          
    //       // this.games = response;
    //       // this.emitGameSubject();
    //     },
    //     (error) => {
    //       console.log('Erreur ! : ' + error);
    //     }
    //   );
    // }

    getGameById(id: number) : Observable <Game[]> {
      return this.httpClient.get<Game[]>('http://localhost:8080/api/rest/v1/game/id/'+id);
    }

    getTasksByGameId(id: number) : Observable <Task[]> {
      return this.httpClient.get<Task[]>('http://localhost:8080/api/rest/v1/task/game/2/'+id);
    }
    
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

    addGameToUser(id: number) {
        const gameObject = {
            title: '',
            genre: '',
            year: 0
        };
        gameObject.title = title;
        gameObject.genre = genre;
        gameObject.year = year;
        this.games.push(gameObject);
        this.emitGameSubject();
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
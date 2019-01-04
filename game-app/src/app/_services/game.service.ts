import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, Subject} from 'rxjs';
import { Game } from '../_models/Game.model';

@Injectable()
export class GameService {
    
    gamesSubject = new Subject<any[]>();

    private games: any;
    readonly BASE_URL = 'http://localhost:8080/api/rest/v1/';
    readonly httpOptions = {headers: new HttpHeaders({'Content-Type':'application/json'})};
    
    constructor(private httpClient: HttpClient){}
    
    emitGameSubject() {
        this.gamesSubject.next(this.games);
    }

    getGamesFromServer(userId: number) {

      this.httpClient
        .get<any[]>(this.BASE_URL+'game/progress/'+userId)

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

    getGameById(id: number) : Observable<Game[]>{
      return this.httpClient.get<Game[]>(this.BASE_URL+'game/id/'+id);

      // let promise = new Promise((resolve, reject) => {
      //   let apiURL = this.BASE_URL+'game/id/'+id;
      //   this.httpClient.get(apiURL)
      //     .toPromise()
      //     .then(
      //       res => { // Success
      //         console.log('getGameById : '+res);
      //         resolve();
      //       }
      //     );
      // });
      // return promise;
    }
    
    saveGameToServer() {
      this.httpClient
        .post(this.BASE_URL+'game', this.games, this.httpOptions)
        
        .subscribe(
          () => {
            console.log('Enregistrement terminé !');
          },
          (error) => {
            console.log('Erreur ! : ' + error);
          }
      );
    }

    addGameToUser(userId: number, gameId: number) : Observable<boolean>{
        const gameObject = {
          gameId: 0,
          userId: 0,
          completion: 0
        };
        gameObject.userId = userId;
        gameObject.gameId = gameId;

      this.httpClient
        .post(this.BASE_URL+'progress', gameObject, this.httpOptions)
        
        .subscribe(
          () => {
            console.log('Enregistrement terminé !');
            return true;
          },
          (error) => {
            console.log('Erreur ! : ' + error);
            return false;
          }
      );

      return new Observable(null);
    }

    searchGames(search: string) : Observable <Game[]> {
      return this.httpClient.get<Game[]>(this.BASE_URL+'game/name/'+search)
    }
}
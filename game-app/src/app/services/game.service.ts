import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { ConfigService } from './config.service';
import { Response } from '@angular/http';

@Injectable()
export class GameService {
    
    gamesSubject = new Subject<any[]>();
    
    private games;

    constructor(private httpClient: HttpClient, private configService: ConfigService){}
    
    emitGameSubject() {
        this.gamesSubject.next(this.games);
    }
    
    getGameById(id: number) {
      this.httpClient
      .get<any[]>('http://localhost:8080/game/id/'+id)
      .subscribe(
        (response) => {
          console.log(response);
          
          return response;
        },
        (error) => {
          console.log('Erreur ! : ' + error);
        }
      );
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

    getGamesFromServer2() {
      // const httpOptions = {
      //   headers: new HttpHeaders({
      //     'Access-Control-Allow-Origin':'*'
      //   })
      // };

      this.httpClient
        .get<any[]>('http://localhost:8080/game/progress')
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

    // getGamesFromServer2(){
    //     this.configService.getGamesFromServer()
    //       .subscribe(data => {
    //         let array: any[] = [];
    //         console.log(data['entity']);
    //         console.log(data['entity'][0][0]);
    //         console.log(data['entity'][1]);

    //         var i = 0;
    //         data['entity'].forEach(element => {
    //           array[i].push(element[0][0].push(element[1][1]))
    //           i++;
    //         });
    //         this.games = array;
    //         console.log(this.games);
    //       },
    //       (error) => {
    //         console.log('Erreur ! : ' + error);
    //       }
    //       );
    // }

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
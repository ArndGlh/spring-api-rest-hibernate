import { Component } from '@angular/core';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent { 
    loggin: any;

    constructor() { }

    ngOnInit() {
        if (localStorage.getItem('currentUser')) { // TODO
            this.loggin = true;
        }else {
            this.loggin = false;
        }
    }
}
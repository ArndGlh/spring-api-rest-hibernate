import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { GameService } from './services/game.service';
import { ConfigService } from './services/config.service';
import { AuthComponent } from './auth/auth.component';
import { GameViewComponent } from './game-view/game-view.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthService } from './services/auth.service';
import { SingleGameComponent } from './single-game/single-game.component';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { AuthGuard } from './services/auth-gard.service';
import { EditGameComponent } from './edit-game-component/edit-game-component.component';
import { UserService } from './services/UserService'
import { UserListComponent } from './user-list-component/user-list-component.component';
import { NewUserComponent } from './new-user/new-user.component';

const appRoutes: Routes = [
  { path: 'games', canActivate: [AuthGuard], component: GameViewComponent },
  { path: 'games/:id', canActivate: [AuthGuard], component: SingleGameComponent },
  { path: 'edit', canActivate: [AuthGuard], component: EditGameComponent},
  { path: 'users', canActivate: [AuthGuard], component: UserListComponent },
  { path: 'new-user',canActivate: [AuthGuard],  component: NewUserComponent },
  { path: 'auth', component: AuthComponent },
  { path: '', canActivate: [AuthGuard], component: GameViewComponent },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: 'not-found' }
];

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    GameViewComponent,
    SingleGameComponent,
    FourOhFourComponent,
    EditGameComponent,
    UserListComponent,
    NewUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule
  ],
  providers: [
    GameService,
    AuthService,
    AuthGuard,
    UserService,
    ConfigService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }

import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { SingleGameComponent } from './single-game/single-game.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { GameViewComponent } from './game-view/game-view.component';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'games', canActivate: [AuthGuard], component: GameViewComponent },
    { path: 'games/:id', canActivate: [AuthGuard], component: SingleGameComponent },
    { path: 'edit', canActivate: [AuthGuard], component: EditGameComponent},

    // otherwise redirect to home
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);
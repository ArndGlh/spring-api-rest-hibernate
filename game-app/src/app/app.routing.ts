import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { SingleGameComponent } from './single-task/single-task.component';
import { NewGameComponent } from './new-task/new-task.component';
import { GameViewComponent } from './task-view/task-view.component';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'games', canActivate: [AuthGuard], component: GameViewComponent },
    { path: 'games/:id', canActivate: [AuthGuard], component: SingleGameComponent },
    { path: 'browse', canActivate: [AuthGuard], component: NewGameComponent},

    // otherwise redirect to home
    { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);
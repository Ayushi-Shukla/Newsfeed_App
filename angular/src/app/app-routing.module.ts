import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { AdminwelcomeComponent } from './adminwelcome/adminwelcome.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SearchedListComponent } from './searched-list/searched-list.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthguardGuard } from 'src/app/authguard.guard'
import { from } from 'rxjs';
import { UserlistComponent } from './userlist/userlist.component';

const routes: Routes = [
  {
    path:'',
    component: HomepageComponent
  },
  {
    path: 'searchedList',
    component: SearchedListComponent,
    canActivate:[AuthguardGuard]
  },
  {
    path: 'adminwelcome',
    component: AdminwelcomeComponent,
    canActivate:[AuthguardGuard]
  },
  {
    path:'logout',
    component: LogoutComponent,
    canActivate:[AuthguardGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'welcome',
    component: WelcomeComponent,
    canActivate:[AuthguardGuard]
  },
  {
    path: 'userlist',
    component: UserlistComponent,
    canActivate:[AuthguardGuard]
  },
  // {
  //   path: 'news',
  //   component: WelcomeComponent,
  //   canActivate:[AuthguardGuard]
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

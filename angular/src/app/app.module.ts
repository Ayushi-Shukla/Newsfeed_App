import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { AdminwelcomeComponent } from './adminwelcome/adminwelcome.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SearchedListComponent } from './searched-list/searched-list.component';
import { LogoutComponent } from './logout/logout.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthguardGuard } from './authguard.guard';
import { httpInterceptorProviders } from './auth/auth-interceptor';
import { UserlistComponent } from './userlist/userlist.component';

@NgModule({
  declarations: [
    AppComponent,  WelcomeComponent, HomepageComponent, SearchedListComponent,
    SignupComponent, LoginComponent,  AdminwelcomeComponent, LogoutComponent, UserlistComponent
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [AuthguardGuard,httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

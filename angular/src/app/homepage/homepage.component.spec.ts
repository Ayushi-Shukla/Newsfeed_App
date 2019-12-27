import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AppRoutingModule } from '../app-routing.module';

import { By } from '@angular/platform-browser';
import { HomepageComponent } from './homepage.component';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LogoutComponent } from '../logout/logout.component';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from '../signup/signup.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserlistComponent } from '../userlist/userlist.component';

describe('HomepageComponent', () => {
  let component: HomepageComponent;
  let fixture: ComponentFixture<HomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserlistComponent,WelcomeComponent ,SignupComponent,LoginComponent, HomepageComponent, SearchedListComponent, AdminwelcomeComponent, LogoutComponent ],
      imports: [ FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});

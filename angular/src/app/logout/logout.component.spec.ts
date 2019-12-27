import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoutComponent } from './logout.component';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from '../signup/signup.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserlistComponent } from '../userlist/userlist.component';

describe('LogoutComponent', () => {
  let component: LogoutComponent;
  let fixture: ComponentFixture<LogoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

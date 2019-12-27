import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeComponent } from './welcome.component';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { SignupComponent } from '../signup/signup.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from '../userservice/userservice.service';
import { By } from '@angular/platform-browser';
import { UserlistComponent } from '../userlist/userlist.component';

describe('WelcomeComponent', () => {
  let component: WelcomeComponent;
  let fixture: ComponentFixture<WelcomeComponent>;
  let userservice: UserService;
  let service:UserService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service=TestBed.get(UserService);

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call the searchNews function', () => {
    spyOn(component, 'search').and.callFake(()=>{
    service.search('water');
    });
    let spy=spyOn(service, 'search');
    component.search();
    expect(spy).toHaveBeenCalledTimes(1);
  });
  
  
  it('should call the searchedListNews function', () => {
    spyOn(component, 'searchedList').and.callFake(()=>{
    service.getSearchedList('ayushi@gmail.com');
    });
    let spy=spyOn(service, 'getSearchedList');
    component.searchedList();
    expect(spy).toHaveBeenCalledTimes(1);
  });

  it('should call the Logout function', () => {
    let href = fixture.debugElement.query(By.css('#logout')).nativeElement.getAttribute('href');
    expect(href).toEqual('/logout');
  });

});

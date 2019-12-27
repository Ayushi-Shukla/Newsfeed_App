import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminwelcomeComponent } from './adminwelcome.component';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { SignupComponent } from '../signup/signup.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserlistComponent } from '../userlist/userlist.component';
import { By } from '@angular/platform-browser';
import { UserService } from '../userservice/userservice.service';


describe('AdminwelcomeComponent', () => {
  let component: AdminwelcomeComponent;
  let fixture: ComponentFixture<AdminwelcomeComponent>;
  let service:UserService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminwelcomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service=TestBed.get(UserService);
  });



  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should click the search user button', () => {
    let spy1=spyOn(component, 'searchuser');
    fixture.debugElement.query(By.css('#searchbtn')).nativeElement.click();
    fixture.detectChanges();
    expect(spy1).toHaveBeenCalled();
  });
    

  it('should call the getUserDetails function', () => {
      spyOn(component, 'searchuser').and.callFake(()=>{
      service.getUserDetails('ayushi');
      });
      let spy=spyOn(service, 'getUserDetails');
      component.searchuser();
      expect(spy).toHaveBeenCalledTimes(1);
  });

  it('should call the getAllUsers function', () => {
    spyOn(component, 'getallusers').and.callFake(()=>{
    service.getAllUsers();
    });
    let spy=spyOn(service, 'getAllUsers');
    component.getallusers();
    expect(spy).toHaveBeenCalledTimes(1);
});


it('should call the searchNews function', () => {
  spyOn(component, 'search').and.callFake(()=>{
  service.search('water');
  });
  let spy=spyOn(service, 'search');
  component.search();
  expect(spy).toHaveBeenCalledTimes(1);
});



it('should call the previouslySearchedNews function', () => {
  spyOn(component, 'searchedList').and.callFake(()=>{
  service.getSearchedList('ayushi@gmail.com');
  });
  let spy=spyOn(service, 'getSearchedList');
  component.searchedList();
  expect(spy).toHaveBeenCalledTimes(1);
});


});

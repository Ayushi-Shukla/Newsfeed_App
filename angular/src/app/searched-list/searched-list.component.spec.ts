import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedListComponent } from './searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { SignupComponent } from '../signup/signup.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserlistComponent } from '../userlist/userlist.component';
import { UserService } from '../userservice/userservice.service';
import { By } from '@angular/platform-browser';

describe('SearchedListComponent', () => {
  let component: SearchedListComponent;
  let fixture: ComponentFixture<SearchedListComponent>;
  let service: UserService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service=TestBed.get(UserService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should call the goBack function', () => {
    let spy1=spyOn(component, 'goback');
    fixture.debugElement.query(By.css('#goback')).nativeElement.click();
    fixture.detectChanges();
    expect(spy1).toHaveBeenCalledTimes(1);
  });

  
  it('should call the remove function', () => {
    spyOn(component, 'remove').and.callFake(()=>{
    service.remove(29);
    });
    let spy=spyOn(service, 'remove');
    component.remove(29);
    expect(spy).toHaveBeenCalled();
});



});

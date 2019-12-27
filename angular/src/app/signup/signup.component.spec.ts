import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { DebugElement } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LogoutComponent } from '../logout/logout.component';
import { By } from '@angular/platform-browser';
import { UserlistComponent } from '../userlist/userlist.component';
import { UserService } from '../userservice/userservice.service';
import { User } from '../userservice/user';

describe('Signup', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let service: UserService;
  let user1: User;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service=TestBed.get(UserService);
  });

  it('is component defined', () => {
    expect(component).toBeDefined();
  });



 it('should call the onSubmit method', () => {
  spyOn(component, 'onSubmit').and.callFake(()=>{
    service.register1(user1);
    });
    let spy=spyOn(service, 'register1');
    component.onSubmit();
    expect(spy).toHaveBeenCalledTimes(1);
  });


  it('valid form', () => {
    let username = component.form.controls["username"];
    username.setValue("karma");
    let email = component.form.controls["email"];
    email.setValue("karma@gmail.com")
    let password = component.form.controls["password"];
    password.setValue("karma123");
    expect(component.form.valid).toBeTruthy();
  });


  it('invalid form', () => {
    let username = component.form.controls["username"];
    username.setValue("Ay");
    let email = component.form.controls["email"];
    email.setValue("Ayushi@gmail.com")
    let password = component.form.controls["password"];
    password.setValue("aa1234");
    expect(component.form.valid).toBeFalsy();
  });


  it('Minimum length username required', () => {
    let username = component.form.controls["username"];
    username.setValue("Ay");
    expect(component.form.controls["username"].valid).toBeFalsy();
  });

  it('Maximum length username required', () => {
    let username = component.form.controls["username"];
    username.setValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    expect(component.form.controls["username"].valid).toBeFalsy();
  });


  it('email pattern validation for signup', ()=>{
    let email = component.form.controls["email"];
    email.setValue("abcd.com");
    let errors = email.errors|| {};
    expect(errors['email']).toBeTruthy();
  });


  it('email required for login', ()=>{
    let email = component.form.controls["email"];
    let errors = email.errors|| {};
    expect(errors['required']).toBeTruthy();
  });


  it('password required for login', ()=>{
    let password = component.form.controls["password"];
    let errors = password.errors|| {};
    expect(errors['required']).toBeTruthy();
  });


  it('password minimum length for signup', ()=>{
    let password = component.form.controls["password"];
    password.setValue("aa");
    let errors = password.errors|| {};
    expect(errors['minlength']).toBeTruthy();
  });


  it('invalid email and password', () => {
    let email = component.form.controls["email"];
    email.setValue("qqqq")
    let password = component.form.controls["password"];
    password.setValue("aa12");
    expect(component.form.controls["email"].valid).toBeFalsy();
    expect(component.form.controls["password"].valid).toBeFalsy();
  });


  it('All empty fields', () => {
    let username = component.form.controls["username"];
    username.setValue("");
    let email = component.form.controls["email"];
    email.setValue("")
    let password = component.form.controls["password"];
    password.setValue("");
    expect(component.form.controls["username"].valid).toBeFalsy();
    expect(component.form.controls["email"].valid).toBeFalsy();
    expect(component.form.controls["password"].valid).toBeFalsy();
    expect(component.form.valid).toBeFalsy();
  });
})

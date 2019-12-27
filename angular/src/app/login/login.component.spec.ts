import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SearchedListComponent } from '../searched-list/searched-list.component';
import { AdminwelcomeComponent } from '../adminwelcome/adminwelcome.component';
import { LogoutComponent } from '../logout/logout.component';
import { SignupComponent } from '../signup/signup.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { By } from '@angular/platform-browser';
import { UserlistComponent } from '../userlist/userlist.component';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserlistComponent,SearchedListComponent,AdminwelcomeComponent,LoginComponent,LogoutComponent,AdminwelcomeComponent,LoginComponent, SignupComponent, HomepageComponent, WelcomeComponent],
      imports: [FormsModule, ReactiveFormsModule, AppRoutingModule, HttpClientModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call the onsubmit', () => {
    let spy1=spyOn(component, 'onSubmit');
    let email = component.form.controls["email"];
    email.setValue("ayushi@gmail.com")
    let password = component.form.controls["password"];
    password.setValue("ayushi123");
    let bb = fixture.debugElement.query(By.css('form')).triggerEventHandler('submit', null);
    fixture.detectChanges();
    expect(spy1).toHaveBeenCalled();
  });


  it('valid login', () => {
    let email = component.form.controls["email"];
    email.setValue("ayushi@gmail.com")
    let password = component.form.controls["password"];
    password.setValue("ayushi123");
    expect(component.form.valid).toBeTruthy();
  });


  it('invalid email and password', () => {
    let email = component.form.controls["email"];
    email.setValue("qqqq")
    let password = component.form.controls["password"];
    password.setValue("aa12");
    expect(component.form.controls["email"].valid).toBeFalsy();
    expect(component.form.controls["password"].valid).toBeFalsy();
  });

  it('invalid login with all empty fields', () => {
    let email = component.form.controls["email"];
    email.setValue("")
    let password = component.form.controls["password"];
    password.setValue("");
    expect(component.form.valid).toBeFalsy();
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

  it('email pattern validation for signup', ()=>{
    let email = component.form.controls["email"];
    email.setValue("abcd.com");
    let errors = email.errors|| {};
    expect(errors['email']).toBeTruthy();
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



});

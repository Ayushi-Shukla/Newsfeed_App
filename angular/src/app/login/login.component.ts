import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, FormsModule, Validators, FormBuilder, NgForm, EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/userservice/userservice.service'
import { User } from 'src/app/userservice/user'
import { TokenstorageService } from '../auth/tokenstorage.service';
import { from } from 'rxjs';
import { Roles } from '../userservice/Roles';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  password = ''; activestatus: boolean;
  email = '';
  error: any;
  status: boolean;
  statusCreation = '';
  form: FormGroup;
  submitted: boolean = false;
  userservice: UserService;
  u = new User();
  isLoggedIn = false;
  isLoginFailed = false;
  roles = new Roles();

  constructor(private fb: FormBuilder, private myRoute: Router, userservice1: UserService, private tokenStorage: TokenstorageService) {
    this.userservice = userservice1;
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit() {
  }


  get f() {
    return this.form.controls;
  }


  onSubmit() {
    this.submitted = true
    if (this.form.valid) {
      this.u.email = this.f.email.value;
      this.u.password = this.f.password.value;
      this.userservice.find(this.u).subscribe(data => {
        console.log(this.u.email);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.userName);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.roles.roleName
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles.roleName = this.tokenStorage.getAuthorities();
        console.log(this.roles.roleName);
        if (this.tokenStorage.getAuthorities() === '[{"authority":"ROLE_ADMIN"}]') {
          this.myRoute.navigate(['adminwelcome']);
        }
        else if (this.tokenStorage.getToken() === 'null') {
          this.error = "not a valid user";
          this.myRoute.navigate(['login']);
        }
        else {
          this.myRoute.navigate(['welcome']);
        }

        this.error = "Access denied";
      },
        error => {
          this.error = error;
          if (this.error.status = 409) {
            this.error = "Invalid credentials! Please try again";
          }
        }
      );
    }
    if (this.form.invalid) {
      this.error = "Please follow the instructions and fill again!";
    }
  }

  reloadPage() {
    window.location.reload();
  }

}

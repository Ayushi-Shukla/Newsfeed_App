import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/userservice/userservice.service'
import { User } from 'src/app/userservice/user'
import { from } from 'rxjs';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  username = '';
  password = '';
  email = '';
  error1 = '';
  error: any;
  status = '';
  form: FormGroup;
  submitted: boolean = false;
  userservice: UserService;
  u = new User();

  constructor(private fb: FormBuilder, private myRoute: Router, userservice1: UserService) {
    this.userservice = userservice1;
    this.form = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
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
      this.u.username = this.f.username.value;
      this.u.email = this.f.email.value;
      this.u.password = this.f.password.value;
      this.u.activeStatus = true;
      this.u.roles = 2;
      this.userservice.register1(this.u).subscribe(data => {
        this.error1 = "You have been successfully registered. Please click login to continue!";
        //alert("You have been successfully registered. You will be redirected to the login page!");

      },
        error => {
          this.error = error;
          if (this.error.status = 409) {
            console.log(this.error);
            this.error1 = this.error.error.error;
          }
        }
      );
    }
    if (this.form.invalid) {
      this.error1 = "Please follow the instructions and fill again!";
    }
  }

  loginpage() {
    this.myRoute.navigate(['signup']);
  }

}

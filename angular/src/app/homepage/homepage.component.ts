import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private myRoute: Router) { }

  ngOnInit() {
  }
  moveToLogin() {
    this.myRoute.navigate(["login"]);
  }
  moveToSignup() {
    this.myRoute.navigate(["signup"]);
  }
  moveToadminLogin() {
    this.myRoute.navigate(["adminlogin"]);
  }

}

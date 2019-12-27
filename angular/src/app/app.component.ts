import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenstorageService } from './auth/tokenstorage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'newssearchPrac';

  constructor(private router: Router,private tokenService:TokenstorageService){

  }
    ngOnInit() {
  
  // this.router.navigate(['']);
  //  this.tokenService.signOut();
    }
  
}

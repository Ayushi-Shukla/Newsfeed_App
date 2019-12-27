import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../userservice/userservice.service';
import { User } from '../userservice/user';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchedNews } from '../userservice/SearchedNews';
import { TokenstorageService } from '../auth/tokenstorage.service';

@Component({
  selector: 'app-adminwelcome',
  templateUrl: './adminwelcome.component.html',
  styleUrls: ['./adminwelcome.component.css']
})
export class AdminwelcomeComponent implements OnInit {

  searchText = ''; username = ''; searchText1 = ''
  error: any; news: any[];
  activeStatus: boolean;
  searchedNews = new SearchedNews();
  status: boolean;
  statusCreation = '';
  isavailable: boolean;
  userList: User[];
  form: FormGroup;
  submitted: boolean = false;
  u = new User();
  show: boolean = true;
  show1: boolean = true;

  constructor(private fb: FormBuilder ,private userservice: UserService, private tokenstorage: TokenstorageService, private router: Router, private route: ActivatedRoute) {
   
}

  ngOnInit() {
    this.isavailable = true;
    console.log(this.userservice.languageCode);
    this.userservice.getAllNews().subscribe(
      data => {
        this.news = data.articles;
        console.log(data);
        console.log(this.news);
      });

  }



  searchuser() {
    this.isavailable = false;
    this.show = false;
    this.show1 = true;
    console.log(this.searchText1);
    this.userservice.getUserDetails(this.searchText1).subscribe(
      data => {
        this.userList = data;
        console.log(this.userList);
      });
  }



  getallusers() {
    this.isavailable = false;
    this.show1 = false;
    this.show = true;

    this.userservice.getAllUsers().subscribe(
      data => {
        this.userList = data;
        console.log(data);
      }
    )
  }



  get f() {
    return this.form.controls;
  }



  search() {
    this.searchedNews.searchwords = this.searchText;
    this.searchedNews.email = this.tokenstorage.getUserName();
    this.u.email=this.tokenstorage.getUserName();
    this.searchedNews.analystdetails=this.u;
    
    this.userservice.search(this.searchText).subscribe(
      data => {
        console.log(this.searchText);
        console.log(this.tokenstorage.getUserName());
        this.news = [];
        this.news = data.articles;
        console.log(data);
      });
    this.userservice.searchedWords(this.searchedNews).subscribe(
      data => {
      });
  }

  searchedList() {
    console.log(this.username);
    this.router.navigate(['searchedList']);
  }


  blacklistUser(id, activeStatus) {
    console.log(id);
    console.log(activeStatus);
    if (activeStatus = true)
      this.userservice.blacklistUser(id).subscribe(
        data => {

          this.getallusers();


        });
  }

  blacklistUser1(id, activeStatus) {
    console.log(id);
    console.log(activeStatus);
    if (activeStatus = true)
      this.userservice.blacklistUser(id).subscribe(
        data => {

          this.searchuser();


        });
  }


  goback(){
  this.ngOnInit();
  }

}

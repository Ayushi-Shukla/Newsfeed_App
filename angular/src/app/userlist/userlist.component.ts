import { Component, OnInit } from '@angular/core';
import { SearchedNews } from '../userservice/SearchedNews';
import { User } from '../userservice/user';
import { FormGroup } from '@angular/forms';
import { UserService } from '../userservice/userservice.service';
import { TokenstorageService } from '../auth/tokenstorage.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {


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

  constructor(private userservice: UserService, private tokenstorage: TokenstorageService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.isavailable = false;
    console.log(this.searchText1);
    this.userservice.getUserDetails(this.searchText1).subscribe(
      data => {
        this.userList = data;
        console.log(this.userList);
      });
  }
  get f() {
    return this.form.controls;
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

  getallusers() {
  this.isavailable = false;

    this.userservice.getAllUsers().subscribe(
      data => {
        this.userList = data;
        console.log(data);
      }
    )
  }
}

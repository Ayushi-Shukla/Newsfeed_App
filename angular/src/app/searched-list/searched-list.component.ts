import { Component, OnInit } from '@angular/core';
import { SearchedNews } from 'src/app/userservice/SearchedNews';
import { UserService } from 'src/app/userservice/userservice.service'
import { Router, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { User } from '../userservice/user';
import { TokenstorageService } from '../auth/tokenstorage.service';

@Component({
  selector: 'app-searched-list',
  templateUrl: './searched-list.component.html',
  styleUrls: ['./searched-list.component.css']
})
export class SearchedListComponent implements OnInit {
  u = new User();
  username = '';

  searchedList: SearchedNews[];

  constructor(private userservice: UserService, private tokenStorage: TokenstorageService, private router: Router, private route: ActivatedRoute) { }



  ngOnInit() {
    this.userservice.getSearchedList(sessionStorage.getItem('AuthuserName')).subscribe(
      data => {
        this.searchedList = data;
      });
  }





  remove(id) {
    this.userservice.remove(id).subscribe(
      data => {
        this.userservice.getSearchedList(sessionStorage.getItem('AuthuserName')).subscribe(
          data => {
            this.searchedList = data;
          });

      });
  }





  back() {
    this.router.navigate(['news', this.u.email]);
  }


  goback(){
    
    if (this.tokenStorage.getAuthorities() === '[{"authority":"ROLE_ADMIN"}]') {
      this.router.navigate(['adminwelcome']);
    }
    else if (this.tokenStorage.getAuthorities() === '[{"authority":"ROLE_USER"}]') {
      this.router.navigate(['welcome']);
    }
    }
}

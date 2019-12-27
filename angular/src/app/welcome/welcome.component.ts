import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../userservice/userservice.service';
import { SearchedNews } from 'src/app/userservice/SearchedNews';
import { News } from '../userservice/news';
import { User } from '../userservice/user';
import { TokenstorageService } from '../auth/tokenstorage.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  form: FormGroup;
  searchText = '';
  username = ''; email = '';
  news: any[];
  u = new User();
  searchedNews = new SearchedNews();





  constructor(private fb: FormBuilder, private tokenstorage: TokenstorageService,
    private userservice: UserService, private router: Router,
    private route: ActivatedRoute) {
    // this.form = this.fb.group({
    //    search: ['', Validators.required],
    // })
  }




  get f() {
    return this.form.controls;
  }





  ngOnInit() {
    console.log(this.userservice.languageCode);
    this.userservice.getAllNews().subscribe(
      data => {
        this.news = data.articles;
        console.log(data);
        console.log(this.news);
      });
  }



  search() {
    this.searchedNews.searchwords = this.searchText;
    this.searchedNews.email = this.tokenstorage.getUserName();
    this.u.email = this.tokenstorage.getUserName();
    this.searchedNews.analystdetails = this.u;
    console.log(this.searchedNews);

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
    this.router.navigate(['searchedList']);
  }


}
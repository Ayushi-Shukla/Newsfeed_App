import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';
import { News } from './news';
import { Observable, throwError } from 'rxjs';
import { SearchedNews } from './SearchedNews';
import { JwtResponse } from '../auth/JwtResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http: HttpClient;
  languageCode: any = '';
  u = new User();

  api_key = '4af29e3b868541a3bc9c8f10cb2467e1';
  username: string;
  constructor(h: HttpClient) {
    this.http = h;
  }

  public register1(user: User) {
    console.log(user.username); console.log(user.email);
    console.log(user.password);
    return this.http.post("http://localhost:2525/api/signup", user)
  }

  public find(user: User) {
    console.log(user.email);
    console.log(user.password);
    return this.http.post<JwtResponse>("http://localhost:2525/api/login", user)
  }

  public findadmin(user: User) {
    console.log(user.email);
    console.log(user.password);
    return this.http.post("http://localhost:2525/api/adminlogin", user)
  }


  public search(searchText): Observable<any> {
    return this.http.get('https://newsapi.org/v2/everything?q=' + searchText + '&from=2019-02-12&sortBy=popularity&apiKey=' + this.api_key);
  }

  getAllNews(): Observable<any> {
    return this.http.get('https://newsapi.org/v2/everything?q="a"&language=en&apiKey=' + this.api_key);
  }

  public searchedWords(searchText: SearchedNews) {
    console.log(searchText);
    return this.http.post("http://localhost:2525/api/searchedText", searchText)
  }

  public getSearchedList(userName: string) {
    return this.http.get<SearchedNews[]>("http://localhost:2525/api/searchList/" + userName);
  }

  public remove(id: number): Observable<any> {
    return this.http.get("http://localhost:2525/api/remove/" + id, { responseType: 'text' });
  }

  public getUserDetails(username: string) {
    console.log(username);
    return this.http.get<User[]>("http://localhost:2525/api/searchuserList/" + username);
  }

  public blacklistUser(username: String) {
    return this.http.get<boolean>("http://localhost:2525/api/blacklistuser/" + username);
  }

  public getAllUsers() {
    return this.http.get<User[]>("http://localhost:2525/api/getallusers/")
  }

}

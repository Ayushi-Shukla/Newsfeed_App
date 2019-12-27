import { Injectable } from '@angular/core';

const Token_key='AuthToken';
const userName_Key='AuthuserName';
const Authorities_Key='AuthAuthorities';
@Injectable({
  providedIn: 'root'
})
export class TokenstorageService {

  constructor() { }
  public saveToken(token: string) {
    window.sessionStorage.removeItem(Token_key);
    window.sessionStorage.setItem(Token_key, token);
  }
  signOut() {
    window.sessionStorage.clear();
  }
  public saveUsername(username: string) {
    window.sessionStorage.removeItem(userName_Key);
    window.sessionStorage.setItem(userName_Key, username);
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(Authorities_Key);
    window.sessionStorage.setItem(Authorities_Key, JSON.stringify(authorities));
  }

  public getToken(): string {
    return sessionStorage.getItem(Token_key);
  }
  public getUserName(): string {
    return sessionStorage.getItem(userName_Key);
  }
  public getAuthorities(): string {
    return sessionStorage.getItem(Authorities_Key);
  }
}

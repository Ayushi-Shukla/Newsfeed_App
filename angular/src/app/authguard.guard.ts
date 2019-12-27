import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenstorageService } from './auth/tokenstorage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {
  path: ActivatedRouteSnapshot[];
  route: ActivatedRouteSnapshot;
constructor(private router:Router,private tokenservice:TokenstorageService){}
canActivate(

    next: ActivatedRouteSnapshot,

    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      let token: string;
      token=window.sessionStorage.getItem('AuthToken');
      if(token!=null)
      {
        return true;
      }
      this.router.navigate(['']);
     
       this.tokenservice.signOut();

    return false;
    
      
  }
}

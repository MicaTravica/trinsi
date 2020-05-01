import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { httpOptions } from 'src/app/util/http-util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(environment.restPath + '/login', { username, password },
      { headers: httpOptions(),
        responseType: 'text'
      });
  }

  isLoggedIn(): boolean {
    if (!localStorage.getItem('token')) {
      return false;
    }
    return true;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUserRole() {
    const token = this.getToken();
    const jwt: JwtHelperService = new JwtHelperService();
    if (!token) {
      return 'GUEST';
    }
    const info = jwt.decodeToken(token);
    return info.role[0].authority;
  }
}

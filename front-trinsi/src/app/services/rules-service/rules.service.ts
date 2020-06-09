import { Injectable } from '@angular/core';
import { AuthService } from '../auth-service/auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { authHttpOptionsText, authHttpOptions } from 'src/app/util/http-util';

@Injectable({
  providedIn: 'root'
})
export class RulesService {

  rulesUrl: string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.rulesUrl = environment.restPath + '/rules';
  }

  add(s: string) {
    return this.http.post(this.rulesUrl, s,
      {
        headers: authHttpOptionsText(this.authService.getToken())
      });
  }
}

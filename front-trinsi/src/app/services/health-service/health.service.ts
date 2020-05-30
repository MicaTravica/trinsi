import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth-service/auth.service';
import { environment } from 'src/environments/environment';
import { authHttpOptions } from 'src/app/util/http-util';
import { UserHealth } from 'src/app/models/user-health/user-health.model';

@Injectable({
  providedIn: 'root'
})
export class HealthService {

  healthUrl: string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.healthUrl = environment.restPath + '/health';
  }

  get() {
    return this.http.get(this.healthUrl,
      {
        headers: authHttpOptions(this.authService.getToken())
      });
  }

  add(health: UserHealth) {
    return this.http.post(this.healthUrl, health,
      {
        headers: authHttpOptions(this.authService.getToken()),
      });
  }

  update(health: UserHealth) {
    return this.http.put(this.healthUrl, health,
      {
        headers: authHttpOptions(this.authService.getToken()),
      });
  }

  addTime(time: any) {
    return this.http.put(this.healthUrl + '/time/' + time, null,
      {
        headers: authHttpOptions(this.authService.getToken())
      });
  }

}

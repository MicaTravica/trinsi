import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth-service/auth.service';
import { environment } from 'src/environments/environment';
import { authHttpOptions } from 'src/app/util/http-util';
import { HeartBeatTracking } from 'src/app/models/heart-beat-tracking/heart-beat-tracking.model';

@Injectable({
  providedIn: 'root'
})
export class AlarmService {

  alarmUrl: string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.alarmUrl = environment.restPath + '/alarm';
  }

  heartBeatTracking(hbt: HeartBeatTracking) {
    return this.http.post(this.alarmUrl, hbt,
      {
        headers: authHttpOptions(this.authService.getToken()),
        responseType: 'text'
      });
  }
}

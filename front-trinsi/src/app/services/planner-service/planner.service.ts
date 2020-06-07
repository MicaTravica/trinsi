import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth-service/auth.service';
import { environment } from 'src/environments/environment';
import { authHttpOptions } from 'src/app/util/http-util';
import { Report } from 'src/app/models/report/report.model';

@Injectable({
  providedIn: 'root'
})
export class PlannerService {

  plannerUrl: string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.plannerUrl = environment.restPath + '/planner';
  }

  get() {
    return this.http.get(this.plannerUrl,
      {
        headers: authHttpOptions(this.authService.getToken())
      });
  }

  reports(report: Report) {
    return this.http.post(this.plannerUrl + '/reports', report,
      {
        headers: authHttpOptions(this.authService.getToken())
      });
  }

}

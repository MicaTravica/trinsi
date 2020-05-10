import { Injectable } from '@angular/core';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { authHttpOptions } from 'src/app/util/http-util';
import { AuthService } from '../auth-service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  exerciseUrl: string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.exerciseUrl = environment.restPath + '/exercise';
  }

  add(exercise: Exercise) {
    return this.http.post(this.exerciseUrl, exercise,
      {
        headers: authHttpOptions(this.authService.getToken()),
        responseType: 'text'
      });
  }
}

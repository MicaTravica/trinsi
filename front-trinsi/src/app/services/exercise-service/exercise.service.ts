import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { authHttpOptions } from 'src/app/util/http-util';
import { AuthService } from '../auth-service/auth.service';
import { ExerciseSearch } from 'src/app/models/exercise-search/exercise-search.model';
import { Exercise } from 'src/app/models/exercise/exercise.model';

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

  get(id: number) {
    return this.http.get(this.exerciseUrl + '/' + id,
      {
        headers: authHttpOptions(this.authService.getToken()),
        responseType: 'json'
      });
  }

  add(exercise: Exercise) {
    return this.http.post(this.exerciseUrl, exercise,
      {
        headers: authHttpOptions(this.authService.getToken()),
        responseType: 'json'
      });
  }

  search(exerciseSearch: ExerciseSearch) {
    return this.http.post(this.exerciseUrl + '/search', exerciseSearch,
      {
        headers: authHttpOptions(this.authService.getToken()),
        responseType: 'json'
      });
  }
}

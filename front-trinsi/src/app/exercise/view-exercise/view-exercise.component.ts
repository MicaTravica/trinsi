import { Component, OnInit } from '@angular/core';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { ActivatedRoute } from '@angular/router';
import { Exercise } from 'src/app/models/exercise/exercise.model';

@Component({
  selector: 'app-view-exercise',
  templateUrl: './view-exercise.component.html',
  styleUrls: ['./view-exercise.component.scss']
})
export class ViewExerciseComponent implements OnInit {

  exercise: Exercise;

  constructor(
    private exerciseService: ExerciseService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.get();
  }

  get() {
    // tslint:disable-next-line: no-string-literal
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.exerciseService.get(id).subscribe(
        (data: Exercise) => {
          this.exercise = data;
        }
      );
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { CATEGORY } from 'src/app/models/enums/category.enum';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { Router } from '@angular/router';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { ExerciseSearch } from 'src/app/models/exercise-search/exercise-search.model';

@Component({
  selector: 'app-list-exercise',
  templateUrl: './list-exercise.component.html',
  styleUrls: ['./list-exercise.component.scss']
})
export class ListExerciseComponent implements OnInit {

  exercises: Exercise[];
  displayedColumns: string[] = ['name', 'type', 'weight', 'details'];
  exerciseSearch = new ExerciseSearch('', null, null);
  exerciseType = [EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.STRENGTHS, EXERCISE_TYPE.CARDIO, EXERCISE_TYPE.WEIGHT_LOSS];
  category = [CATEGORY.BEGINNER, CATEGORY.MIDDLE, CATEGORY.ADVANCED];

  constructor(
    private exerciseService: ExerciseService,
    private router: Router
  ) { }

  ngOnInit() {
    this.search();
  }

  search() {
    this.exerciseService.search(this.exerciseSearch).subscribe(
      (data: Exercise[]) => {
        this.exercises = data;
      }
    );
  }

  details(id: number) {
    this.router.navigate(['/exercise/' + id ]);
  }
}

import { Component, OnInit } from '@angular/core';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { ExerciseSearch } from 'src/app/models/exercise-search/exercise-search.model';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { NavigationEnd, Router, Event} from '@angular/router';
import { MatDialog } from '@angular/material';
import { AddExerciseComponent } from '../add-exercise/add-exercise.component';
import { ViewExerciseComponent } from '../view-exercise/view-exercise.component';
import { INTENSITY } from 'src/app/models/enums/intensity.enum';
import { MUSCLES_GROUP } from 'src/app/models/enums/muscles-group.enum';

@Component({
  selector: 'app-list-exercise',
  templateUrl: './list-exercise.component.html',
  styleUrls: ['./list-exercise.component.scss']
})
export class ListExerciseComponent implements OnInit {

  exercises: Exercise[];
  displayedColumns: string[] = ['name', 'type', 'intensity', 'musclesGroup', 'details'];
  exerciseSearch = new ExerciseSearch('', null, null, null);
  exerciseType = [null, EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.MUSCULAR, EXERCISE_TYPE.AEROBIC];
  intensity = [null, INTENSITY.LIGHT, INTENSITY.MODERATE, INTENSITY.VIGOROUS];
  musclesGroup = [null, MUSCLES_GROUP.ARMS, MUSCLES_GROUP.SHOULDERS, MUSCLES_GROUP.CHEST, MUSCLES_GROUP.BACK,
    MUSCLES_GROUP.LEGS, MUSCLES_GROUP.BUTTOCKS, MUSCLES_GROUP.ABDOMEN];

  constructor(
    private exerciseService: ExerciseService,
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog
  ) {
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationEnd) {
        if (this.authService.getUserRole() === 'ROLE_ADMIN') {
          this.displayedColumns.push('change');
       }
      }
    });
  }

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

  add() {
    const dialogRef = this.dialog.open(AddExerciseComponent, {
      width: '70%'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.search();
      }
    });
  }

  change(exercise: Exercise) {
    const dialogRef = this.dialog.open(AddExerciseComponent, {
      width: '70%',
      data: exercise
    });

    dialogRef.afterClosed().subscribe(result => {
      this.search();
    });
  }

  details(exercise: Exercise) {
    const dialogRef = this.dialog.open(ViewExerciseComponent, {
      width: '70%',
      data: exercise
    });
  }

  typeChanged() {
    if (this.exerciseSearch.exerciseType === EXERCISE_TYPE.AEROBIC || this.exerciseSearch.exerciseType === null) {
      this.exerciseSearch.musclesGroup = null;
    }
    if (this.exerciseSearch.exerciseType === EXERCISE_TYPE.MUSCULAR ||
      this.exerciseSearch.exerciseType === EXERCISE_TYPE.STRETCHES || this.exerciseSearch.exerciseType === null) {
      this.exerciseSearch.intensity = null;
    }
  }
}

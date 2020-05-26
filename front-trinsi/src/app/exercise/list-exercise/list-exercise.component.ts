import { Component, OnInit } from '@angular/core';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { CATEGORY } from 'src/app/models/enums/category.enum';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { ExerciseSearch } from 'src/app/models/exercise-search/exercise-search.model';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { NavigationEnd, Router, Event} from '@angular/router';
import { MatDialog } from '@angular/material';
import { AddExerciseComponent } from '../add-exercise/add-exercise.component';
import { ViewExerciseComponent } from '../view-exercise/view-exercise.component';

@Component({
  selector: 'app-list-exercise',
  templateUrl: './list-exercise.component.html',
  styleUrls: ['./list-exercise.component.scss']
})
export class ListExerciseComponent implements OnInit {

  exercises: Exercise[];
  displayedColumns: string[] = ['name', 'type', 'weight', 'details'];
  exerciseSearch = new ExerciseSearch('', null, null, 0, 10);
  exerciseType = [null, EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.STRENGTHS, EXERCISE_TYPE.CARDIO, EXERCISE_TYPE.WEIGHT_LOSS];
  category = [null, CATEGORY.BEGINNER, CATEGORY.MIDDLE, CATEGORY.ADVANCED];
  totalElements = 0;

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
      (data: any) => {
        this.exercises = data.content;
        this.totalElements = data.totalElements;
      }
    );
  }

  onSearch() {
    this.exerciseSearch.pageNum = 0;
    this.search();
  }

  pageChanged(num: number) {
    this.exerciseSearch.pageNum = num - 1;
    this.search();
  }

  add() {
    const dialogRef = this.dialog.open(AddExerciseComponent, {
      width: '33%'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.search();
      }
    });
  }

  change(exercise: Exercise) {
    const dialogRef = this.dialog.open(AddExerciseComponent, {
      width: '33%',
      data: exercise
    });

    dialogRef.afterClosed().subscribe(result => {
      this.search();
    });
  }

  details(exercise: Exercise) {
    const dialogRef = this.dialog.open(ViewExerciseComponent, {
      width: '33%',
      data: exercise
    });
  }
}

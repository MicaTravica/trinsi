import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { MatDialog } from '@angular/material';
import { ViewExerciseComponent } from 'src/app/exercise/view-exercise/view-exercise.component';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { CATEGORY } from 'src/app/models/enums/category.enum';
import { PlannerTimeComponent } from '../planner-time/planner-time.component';

@Component({
  selector: 'app-planner-exercise',
  templateUrl: './planner-exercise.component.html',
  styleUrls: ['./planner-exercise.component.scss']
})
export class PlannerExerciseComponent implements OnChanges {

  @Input() taken: boolean;
  @Output() refreshHealth = new EventEmitter();
  planner: UserPlanner = null;
  displayedColumns: string[] = ['name', 'type', 'weight', 'details'];

  constructor(
    private plannerService: PlannerService,
    private dialog: MatDialog
  ) { }

  ngOnChanges() {
    if (this.taken) {
      this.getPlanner();
    }
  }

  getPlanner() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.planner = data;
        this.planner.exercises.push(new Exercise(1, 'ime', 'opis', EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
      }
    );
  }

  addTime() {
    const dialogRef = this.dialog.open(PlannerTimeComponent, {
      width: '33%',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.refreshHealth.emit();
      }
    });
  }

  details(exercise: Exercise) {
    const dialogRef = this.dialog.open(ViewExerciseComponent, {
      width: '33%',
      data: exercise
    });
  }

}

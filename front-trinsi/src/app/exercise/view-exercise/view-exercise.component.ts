import { Component, OnInit, Inject } from '@angular/core';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { ActivatedRoute } from '@angular/router';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-view-exercise',
  templateUrl: './view-exercise.component.html',
  styleUrls: ['./view-exercise.component.scss']
})
export class ViewExerciseComponent {

  exercise = new Exercise(null, null, null, null, null);

  constructor(
    private exerciseService: ExerciseService,
    private dialogRef: MatDialogRef<ViewExerciseComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Exercise
  ) {
    this.exercise = data;
   }

   onClose() {
    this.dialogRef.close();
  }

}

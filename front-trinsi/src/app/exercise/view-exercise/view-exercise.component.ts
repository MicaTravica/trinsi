import { Component, Inject } from '@angular/core';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-view-exercise',
  templateUrl: './view-exercise.component.html',
  styleUrls: ['./view-exercise.component.scss']
})
export class ViewExerciseComponent {

  exercise = new Exercise(null, null, null, null, null, null, []);
  activeImg = 0;

  constructor(
    private dialogRef: MatDialogRef<ViewExerciseComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Exercise
  ) {
    this.exercise = data;
   }

   onClose() {
    this.dialogRef.close();
  }

  previous() {
    this.activeImg -= 1;
  }

  next() {
    this.activeImg += 1;
  }

}

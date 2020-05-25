import { Component, OnInit, Inject } from '@angular/core';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { CATEGORY } from 'src/app/models/enums/category.enum';
import { ToastrService } from 'ngx-toastr';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-add-exercise',
  templateUrl: './add-exercise.component.html',
  styleUrls: ['./add-exercise.component.scss']
})
export class AddExerciseComponent {

  exercise = new Exercise(null, '', '', EXERCISE_TYPE.STRETCHES, CATEGORY.BEGINNER);
  exerciseType = [EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.STRENGTHS, EXERCISE_TYPE.CARDIO, EXERCISE_TYPE.WEIGHT_LOSS];
  category = [CATEGORY.BEGINNER, CATEGORY.MIDDLE, CATEGORY.ADVANCED];
  action = 'Add';

  constructor(
    private exerciseService: ExerciseService,
    private toastr: ToastrService,
    private dialogRef: MatDialogRef<AddExerciseComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Exercise
  ) {
    if (data != null) {
      this.exercise = data;
      this.action = 'Change';
    }
  }


  onSubmit() {
    if (this.exercise.id == null) {
      this.add();
    } else {
      this.update();
    }
  }

  add() {
    this.exerciseService.add(this.exercise).subscribe(
      (data: number) => {
        this.toastr.success('Successful add!');
        this.dialogRef.close(data);
      }
    );
  }

  update() {
    this.exerciseService.update(this.exercise).subscribe(
      (data: number) => {
        this.toastr.success('Successful changed!');
        this.dialogRef.close(data);
      }
    );
  }

  onClose() {
    this.dialogRef.close();
  }
}

import { Component, OnInit } from '@angular/core';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { CATEGORY } from 'src/app/models/enums/category.enum';
import { ToastrService } from 'ngx-toastr';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';

@Component({
  selector: 'app-add-exercise',
  templateUrl: './add-exercise.component.html',
  styleUrls: ['./add-exercise.component.scss']
})
export class AddExerciseComponent implements OnInit {

  exercise = new Exercise(null, '', '', EXERCISE_TYPE.STRETCHES, CATEGORY.BEGINNER);
  exerciseType = [EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.STRENGTHS, EXERCISE_TYPE.CARDIO, EXERCISE_TYPE.WEIGHT_LOSS];
  category = [CATEGORY.BEGINNER, CATEGORY.MIDDLE, CATEGORY.ADVANCED];

  constructor(
    private exerciseService: ExerciseService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
  }

  add() {
    console.log(this.exercise);
    // this.exerciseService.add(this.exercise).subscribe(
    //   (data: any) => {
    //     this.toastr.success('Successful add!');
    //     this.exercise = data;
    //   }
    // );
  }
}

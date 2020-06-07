import { Component, OnInit } from '@angular/core';
import { EXERCISE_TYPE } from '../models/enums/exercise-type.enum';
import { CATEGORY } from '../models/enums/category.enum';
import { Report } from '../models/report/report.model';
import { PlannerService } from '../services/planner-service/planner.service';
import { MissingExercises } from '../models/missing-exercises/missing-exercises.model';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {

  checkedCategories = [false, false, false];
  checkedExerciseType = [false, false, false, false];

  constructor(
    private plannerService: PlannerService
  ) { }

  ngOnInit() {
  }

  getReport() {
    const categories = [CATEGORY.BEGINNER, CATEGORY.MIDDLE, CATEGORY.ADVANCED];
    const exerciseTypes = [EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.STRENGTHS, EXERCISE_TYPE.CARDIO, EXERCISE_TYPE.WEIGHT_LOSS];
    const categoriesReport = [];
    const exerciseTypeReport = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.checkedCategories.length; i++) {
      if (this.checkedCategories[i]) {
        categoriesReport.push(categories[i]);
      }
    }
    for (let i = 0; i < this.checkedExerciseType.length; i++) {
      if (this.checkedExerciseType[i]) {
        exerciseTypeReport.push(exerciseTypes[i]);
      }
    }
    this.plannerService.reports(new Report(categoriesReport, exerciseTypeReport)).subscribe(
      (data: MissingExercises[]) => {
        console.log(data);
      }
    );
  }

}

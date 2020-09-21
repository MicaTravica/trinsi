import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddExerciseComponent } from './add-exercise/add-exercise.component';
import { ListExerciseComponent } from './list-exercise/list-exercise.component';
import { ViewExerciseComponent } from './view-exercise/view-exercise.component';
import { ExerciseRoutingModule } from './exercise-routing.module';
import { MaterialModule } from '../material.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AddExerciseComponent,
    ListExerciseComponent,
    ViewExerciseComponent
  ],
  imports: [
    CommonModule,
    ExerciseRoutingModule,
    MaterialModule,
    FormsModule
  ],
  entryComponents: [
    AddExerciseComponent,
    ViewExerciseComponent
  ]
})
export class ExerciseModule { }

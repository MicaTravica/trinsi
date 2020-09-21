import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FitnessRoutingModule } from './fitness-routing.module';
import { HealthComponent } from './health/health.component';
import { FitnessComponent } from './fitness/fitness.component';
import { PlannerExerciseComponent } from './planner-exercise/planner-exercise.component';
import { PlannerTimeComponent } from './planner-time/planner-time.component';
import { MaterialModule } from '../material.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    HealthComponent,
    FitnessComponent,
    PlannerExerciseComponent,
    PlannerTimeComponent
  ],
  imports: [
    CommonModule,
    FitnessRoutingModule,
    MaterialModule,
    FormsModule
  ],
  entryComponents: [
    PlannerTimeComponent,
    HealthComponent
  ]
})
export class FitnessModule { }

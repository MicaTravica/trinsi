import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { PlannerExerciseComponent } from '../planner-exercise/planner-exercise.component';
import { MatDialogRef } from '@angular/material';
import { HealthService } from 'src/app/services/health-service/health.service';

@Component({
  selector: 'app-planner-time',
  templateUrl: './planner-time.component.html',
  styleUrls: ['./planner-time.component.scss']
})
export class PlannerTimeComponent {

  time: number;

  constructor(
    private healthService: HealthService,
    private toastr: ToastrService,
    private dialogRef: MatDialogRef<PlannerExerciseComponent>
  ) {

  }


  addTime() {
    this.healthService.addTime(this.time).subscribe(
      () => {
        this.toastr.success('Successful add!');
        this.dialogRef.close(true);
      }
    );
  }

  onClose() {
    this.dialogRef.close();
  }

}

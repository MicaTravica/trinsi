import { Component, OnInit, Inject } from '@angular/core';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { ToastrService } from 'ngx-toastr';
import { PlannerExerciseComponent } from '../planner-exercise/planner-exercise.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-planner-time',
  templateUrl: './planner-time.component.html',
  styleUrls: ['./planner-time.component.scss']
})
export class PlannerTimeComponent {

  time: number;
  id;

  constructor(
    private plannerService: PlannerService,
    private toastr: ToastrService,
    private dialogRef: MatDialogRef<PlannerExerciseComponent>
    // @Inject(MAT_DIALOG_DATA) private data: UserHealth
  ) {

  }


  addTime() {
    this.plannerService.addTime(this.time).subscribe(
      (data: number) => {
        this.toastr.success('Successful add!');
        this.dialogRef.close(data);
      }
    );
  }

  onClose() {
    this.dialogRef.close();
  }

}

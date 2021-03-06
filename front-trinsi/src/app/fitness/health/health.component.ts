import { Component, OnInit, Inject } from '@angular/core';
import { HealthService } from 'src/app/services/health-service/health.service';
import { UserHealth } from 'src/app/models/user-health/user-health.model';
import { ToastrService } from 'ngx-toastr';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.scss']
})
export class HealthComponent {

  health: UserHealth;
  action = 'Add';

  constructor(
    private healthService: HealthService,
    private toastr: ToastrService,
    private dialogRef: MatDialogRef<HealthComponent>,
    @Inject(MAT_DIALOG_DATA) private data: UserHealth
  ) {
    this.health = data;
    if (this.health.id != null) {
      this.action = 'Change';
    }
  }

  onSubmit() {
    if (this.health.id == null) {
      this.addQuestionnaire();
      this.add();
    } else {
      this.update();
    }
  }

  add() {
    this.addQuestionnaire();
    this.healthService.add(this.health).subscribe(
      (data: number) => {
        this.toastr.success('Successful add!');
        this.dialogRef.close(data);
      }
    );
  }

  update() {
    this.healthService.update(this.health).subscribe(
      (data: number) => {
        this.toastr.success('Successful changed!');
        this.dialogRef.close(data);
      }
    );
  }

  onClose() {
    this.dialogRef.close();
  }

  addQuestionnaire() {
    const q = this.health.questionnaire;
    if (q.vigorousDays === 0) {
      q.vigorousMinutes = 0;
    }
    if (q.moderateDays === 0) {
      q.moderateMinutes = 0;
    }
    if (q.walkingDays === 0) {
      q.walkingMinutes = 0;
    }
  }
}

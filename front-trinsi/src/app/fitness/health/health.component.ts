import { Component, OnInit, Inject } from '@angular/core';
import { HealthService } from 'src/app/services/health-service/health.service';
import { UserHealth } from 'src/app/models/user-health/user-health.model';
import { GENDER } from 'src/app/models/enums/gender.enum';
import { ToastrService } from 'ngx-toastr';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.scss']
})
export class HealthComponent {

  health: UserHealth;
  genders = [ GENDER.MALE, GENDER.FEMALE ];
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
      this.add();
    } else {
      this.update();
    }
  }

  add() {
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
}

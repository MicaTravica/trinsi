import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { HealthComponent } from '../health/health.component';
import { HealthService } from 'src/app/services/health-service/health.service';
import { UserHealth } from 'src/app/models/user-health/user-health.model';
import { Questionnaire } from 'src/app/models/questionnaire/questionnaire.model';

@Component({
  selector: 'app-fitness',
  templateUrl: './fitness.component.html',
  styleUrls: ['./fitness.component.scss']
})
export class FitnessComponent implements OnInit {

  health: UserHealth = null;

  constructor(
    private healthService: HealthService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getHealth();
  }

  getHealth() {
    this.healthService.get().subscribe(
      (data: UserHealth) => {
        if (data.id == null) {
          this.health = new UserHealth(null, null, null, null, null, null, null, null,
            new Questionnaire(null, null, null, null, null, null));
        } else {
          this.health = data;
        }
      }
    );
  }

  openHealth() {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '70%',
      data: this.health
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.getHealth();
      }
    });
  }

  twentyEightDays() {
    if (this.health != null && this.health.id != null && !this.health.plannerTaken) {
      const today = new Date();
      const seven = new Date(this.health.lastChanged);
      seven.setDate(seven.getDate() + 28);
      seven.setHours(0);
      seven.setMinutes(0);
      seven.setSeconds(0);
      return seven <= today;
    }
    return false;
  }

  taken() {
    if (this.health != null && this.health.id != null) {
      return this.health.plannerTaken;
    }
    return false;
  }
}

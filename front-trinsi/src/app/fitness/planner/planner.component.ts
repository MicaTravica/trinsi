import { Component, OnInit } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { MatDialog } from '@angular/material';
import { HealthComponent } from '../health/health.component';
import { HealthService } from 'src/app/services/health-service/health.service';
import { UserHealth } from 'src/app/models/user-health/user-health.model';

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.scss']
})
export class PlannerComponent implements OnInit {

  planner: UserPlanner = null;
  health: UserHealth = null;

  constructor(
    private plannerService: PlannerService,
    private healthService: HealthService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getHealthAndPlanner();
  }

  getHealthAndPlanner() {
    this.healthService.get().subscribe(
      (data: UserHealth) => {
        this.health = data;
        if (!this.sevenDays()) {
          this.getPlanner();
        }
      }
    );
  }

  getPlanner() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.planner = data;
      }
    );
  }

  openHealth() {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '33%',
      data: this.health
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.getHealthAndPlanner();
      }
    });
  }

  sevenDays() {
    if (this.health != null && this.health.id != null) {
      const today = new Date();
      const seven = new Date(this.health.lastChanged);
      seven.setDate(seven.getDate() + 7);
      if (today.getDate() >= seven.getDate() && today.getMonth() >= seven.getMonth() && today.getFullYear() >= seven.getFullYear()) {
        return true;
      }
      return false;
    }
    return false;
  }
}

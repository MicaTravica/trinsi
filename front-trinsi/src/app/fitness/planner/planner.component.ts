import { Component, OnInit } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { HealthComponent } from '../health/health.component';
import { UserHealth } from 'src/app/models/user-health/user-health.model';

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.scss']
})
export class PlannerComponent implements OnInit {

  planner = new UserPlanner(null, null, null, null, null, null, null);

  constructor(
    private plannerService: PlannerService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getPlanner();
  }

  getPlanner() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.planner = data;
      }
    );
  }

  addHealth() {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '33%'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.getPlanner();
      }
    });
  }

  changeHealth(health: UserHealth) {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '33%',
      data: health
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }


}
